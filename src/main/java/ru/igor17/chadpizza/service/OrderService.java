package ru.igor17.chadpizza.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ru.igor17.chadpizza.model.Customer;
import ru.igor17.chadpizza.model.Ingredient;
import ru.igor17.chadpizza.model.LnkPizzaIngredient;
import ru.igor17.chadpizza.model.Order;
import ru.igor17.chadpizza.model.OrderPosition;
import ru.igor17.chadpizza.model.Status;
import ru.igor17.chadpizza.repository.ICustomerRepository;
import ru.igor17.chadpizza.repository.IIngredientRepository;
import ru.igor17.chadpizza.repository.ILnkPizzaIngredientRepository;
import ru.igor17.chadpizza.repository.IOrderRepository;
import ru.igor17.chadpizza.repository.IPizzaRepository;
import ru.igor17.chadpizza.view.OrderAddDTO;
import ru.igor17.chadpizza.view.OrderChangeStatusDTO;
import ru.igor17.chadpizza.view.OrderListDTO;
import ru.igor17.chadpizza.view.OrderPositionDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.StreamSupport;

import static ru.igor17.chadpizza.model.Status.PAID;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class OrderService implements IBaseService<Order,OrderListDTO> {

	private final IOrderRepository orderRepository;

	private final ICustomerRepository customerRepository;

	private final IPizzaRepository pizzaRepository;

	private final ILnkPizzaIngredientRepository lnkPizzaIngredientRepository;

	private final IIngredientRepository ingredientRepository;

	public OrderListDTO createEntity(final OrderAddDTO dto) {
		final Order order = orderAddDtoToEntity(dto);
		orderRepository.save(order);
		reduceIngredients(order);
		return entityToOrderListDto(order);
	}

	@Override
	public OrderListDTO getById(final Long id) {
		return orderRepository.findById(id)
				.map(this::entityToOrderListDto)
				.orElseThrow(EntityNotFoundException::new);
	}

	@Override
	public List<OrderListDTO> getAll() {
		return StreamSupport.stream(orderRepository.findAll().spliterator(), false)
				.map(this::entityToOrderListDto)
				.toList();
	}

	@Override
	public OrderListDTO createEntity(OrderListDTO dto) {
		throw new UnsupportedOperationException();
	}

	@Override
	public OrderListDTO updateEntity(OrderListDTO dto) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteEntity(Long id) {
		orderRepository.deleteById(id);
	}

	public List<OrderListDTO> getAllPaid() {
		return orderRepository.findAllByStatus(PAID).stream()
				.map(this::entityToOrderListDto)
				.toList();
	}

	public OrderListDTO changeStatus(final OrderChangeStatusDTO dto) {
		return orderRepository.findById(dto.getOrderId())
				.map(order -> {
					order.setStatus(Status.valueOf(dto.getStatus()));
					return entityToOrderListDto(orderRepository.save(order));
				})
				.orElseThrow(EntityNotFoundException::new);
	}

	public boolean canOrderBeApplied(final OrderAddDTO dto) {
		return dto.getOrderPositions().stream()
				.map(opDto -> {
					final List<LnkPizzaIngredient> lnks =
							lnkPizzaIngredientRepository.findLnkPizzaIngredientsByPizzaId(
									pizzaRepository.findByName(opDto.getPizzaName()).getId()
							);
					for (LnkPizzaIngredient lnk: lnks) {
						if (lnk.getCount() * opDto.getCount() > lnk.getIngredient().getCount()) {
							return false;
						}
					}
					return true;
				})
				.allMatch(b -> b.equals(true));
	}

	private Order orderAddDtoToEntity(final OrderAddDTO dto) {
		final Order order = new Order();

		order.setOrderDateTime(LocalDateTime.now());
		order.setStatus(Status.DRAFT);
		order.setTotalPrice(Float.parseFloat(dto.getOrderTotalPrice()));

		final Customer customer = new Customer();

		customer.setAddress(dto.getCustomerAddress());
		customer.setPhoneNumber(dto.getCustomerPhoneNumber());
		customer.setFirstName(dto.getCustomerFirstName());
		customer.setMiddleName(dto.getCustomerMiddleName());
		customer.setSurname(dto.getCustomerSurname());

		customerRepository.save(customer);
		order.setCustomer(customer);

		List<OrderPosition> orderPositions = dto.getOrderPositions().stream()
				.map(this::orderPositionDtoToEntity)
				.toList();

		orderPositions.forEach(op -> op.setOrder(order));
		order.setOrderPositions(orderPositions);

		return order;
	}

	private OrderListDTO entityToOrderListDto(final Order order) {
		final OrderListDTO dto = new OrderListDTO();

		dto.setId(order.getId());
		dto.setOrderDateTime(order.getOrderDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
		dto.setOrderStatus(order.getStatus().toString());
		dto.setOrderTotalPrice(order.getTotalPrice().toString());

		customerRepository.findById(order.getCustomer().getId())
				.map(customer -> {
					dto.setCustomerFIO(customer.getFirstName() + " " + customer.getSurname() + " " + customer.getMiddleName());
					dto.setCustomerAddress(customer.getAddress());
					dto.setCustomerPhoneNumber(customer.getPhoneNumber());
					dto.setOrderPositions(order.getOrderPositions().stream()
							.map(this::orderPositionToDto)
							.toList());
					return dto;
				}).orElseThrow(EntityNotFoundException::new);

		return dto;
	}

	private OrderPositionDTO orderPositionToDto(final OrderPosition orderPosition) {
		final OrderPositionDTO dto = new OrderPositionDTO();
		dto.setCount(orderPosition.getCount());
		return pizzaRepository.findById(orderPosition.getPizza().getId())
				.map(pizza -> {
					dto.setPizzaName(pizza.getName());
					return dto;
				})
				.orElseThrow(EntityNotFoundException::new);
	}

	private OrderPosition orderPositionDtoToEntity(final OrderPositionDTO dto) {
		final OrderPosition orderPosition = new OrderPosition();

		orderPosition.setCount(dto.getCount());
		orderPosition.setPizza(pizzaRepository.findByName(dto.getPizzaName()));

		return orderPosition;
	}

	private void reduceIngredients(final Order order) {
		order.getOrderPositions()
				.forEach(op -> {
					final List<LnkPizzaIngredient> lnks = lnkPizzaIngredientRepository.findLnkPizzaIngredientsByPizzaId(op.getPizza().getId());
					for (LnkPizzaIngredient lnk : lnks) {
						final Ingredient ingredient = lnk.getIngredient();
						ingredient.setCount(
								ingredient.getCount() - op.getCount() * lnk.getCount()
						);
						ingredientRepository.save(ingredient);
					}
				});
	}

}

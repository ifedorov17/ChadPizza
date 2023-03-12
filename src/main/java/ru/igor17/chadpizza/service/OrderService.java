package ru.igor17.chadpizza.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ru.igor17.chadpizza.dao.CustomerDAO;
import ru.igor17.chadpizza.dao.OrderDAO;
import ru.igor17.chadpizza.dao.OrderPositionDAO;
import ru.igor17.chadpizza.dao.PizzaDAO;
import ru.igor17.chadpizza.model.Customer;
import ru.igor17.chadpizza.model.Order;
import ru.igor17.chadpizza.model.OrderPosition;
import ru.igor17.chadpizza.model.Pizza;
import ru.igor17.chadpizza.model.Status;
import ru.igor17.chadpizza.view.OrderAddDTO;
import ru.igor17.chadpizza.view.OrderChangeStatusDTO;
import ru.igor17.chadpizza.view.OrderListDTO;
import ru.igor17.chadpizza.view.OrderPositionDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static ru.igor17.chadpizza.model.Status.PAID;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class OrderService {

	private final OrderDAO orderDAO;

	private final OrderPositionDAO orderPositionDAO;

	private final CustomerDAO customerDAO;

	private final PizzaDAO pizzaDAO;

	public Order createEntity(final OrderAddDTO dto) {
		final Order order = orderAddDtoToEntity(dto);
		orderDAO.insert(order);

		List<OrderPosition> orderPositions = dto.getOrderPositions().stream()
				.map(this::orderPositionDtoToEntity)
				.toList();

		orderPositions.forEach(op -> op.setOrderID(order.getId()));
		orderPositions.forEach(orderPositionDAO::insert);

		return order;
	}

	public OrderListDTO getById(final Long id) {
		return entityToOrderListDto(orderDAO.getById(id));
	}

	public List<OrderListDTO> getAll() {
		return orderDAO.getAll().stream()
				.map(this::entityToOrderListDto)
				.toList();
	}

	public List<OrderListDTO> getAllPaid() {
		return orderDAO.getAll().stream()
				.filter(order -> PAID.equals(order.getStatus()))
				.map(this::entityToOrderListDto)
				.toList();
	}

	public Order changeStatus(final OrderChangeStatusDTO dto) {
		final Order order = orderDAO.getById(Long.parseLong(dto.getOrderId()));
		order.setStatus(Status.valueOf(dto.getStatus()));
		orderDAO.update(order);
		return order;
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

		customerDAO.insert(customer);
		order.setUserID(customer.getId());

		return order;
	}

	private OrderListDTO entityToOrderListDto(final Order order) {
		final OrderListDTO dto = new OrderListDTO();

		dto.setOrderDateTime(order.getOrderDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
		dto.setOrderStatus(order.getStatus().toString());
		dto.setOrderTotalPrice(order.getTotalPrice().toString());

		final Customer customer = customerDAO.getById(order.getUserID());

		dto.setCustomerFIO(customer.getFirstName() + customer.getSurname() + customer.getMiddleName());
		dto.setCustomerAddress(customer.getAddress());
		dto.setOrderPositions(orderPositionDAO.getOrderPositionsByOrderId(order.getId()).stream()
				.map(this::orderPositionToDto)
				.toList()
		);

		return dto;
	}

	private OrderPositionDTO orderPositionToDto(final OrderPosition orderPosition) {
		final OrderPositionDTO dto = new OrderPositionDTO();
		dto.setCount(orderPosition.getCount());
		final Pizza pizza = pizzaDAO.getById(orderPosition.getPizzaID());
		dto.setPizzaName(pizza.getName());
		return dto;
	}

	private OrderPosition orderPositionDtoToEntity(final OrderPositionDTO dto) {
		final OrderPosition orderPosition = new OrderPosition();

		orderPosition.setCount(dto.getCount());
		orderPosition.setPizzaID(pizzaDAO.getPizzaByName(dto.getPizzaName()).getId());

		return orderPosition;
	}

}

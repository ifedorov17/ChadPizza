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
import ru.igor17.chadpizza.view.OrderListDTO;
import ru.igor17.chadpizza.view.OrderPositionDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class OrderService {

	private final OrderDAO orderDAO;

	private final OrderPositionDAO orderPositionDAO;

	private final CustomerDAO customerDAO;

	private final PizzaDAO pizzaDAO;

	public Order insert(final OrderAddDTO dto) {
		final Order order = orderAddDtoToEntity(dto);
		orderDAO.insert(order);
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

		final Customer customer = customerDAO.get(order.getUserID());

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
		final Pizza pizza = pizzaDAO.get(orderPosition.getPizzaID());
		dto.setPizzaName(pizza.getName());
		return dto;
	}

}

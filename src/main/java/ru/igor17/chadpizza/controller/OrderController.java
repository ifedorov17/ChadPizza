package ru.igor17.chadpizza.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ru.igor17.chadpizza.dao.CustomerDAO;
import ru.igor17.chadpizza.dao.OrderDAO;
import ru.igor17.chadpizza.dao.OrderPositionDAO;
import ru.igor17.chadpizza.dao.PizzaDAO;

import ru.igor17.chadpizza.model.Order;
import ru.igor17.chadpizza.model.OrderPosition;
import ru.igor17.chadpizza.model.Pizza;
import ru.igor17.chadpizza.model.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.igor17.chadpizza.model.Status.PAID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/order")
public class OrderController {

	private final OrderDAO orderDAO;

	private final CustomerDAO customerDAO;

	private final PizzaDAO pizzaDAO;

	private final OrderPositionDAO orderPositionDAO;

	@PostMapping
	public ResponseEntity<Order> create(@RequestBody Order order) {
		/*if (customerDAO.get(order.getUserID()) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find user with given userID");
		}*/
		order.setOrderDateTime(LocalDateTime.now());
		order.setStatus(Status.DRAFT);
		order.setTotalPrice(0.0f);
		orderDAO.insert(order);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	@PutMapping("/position")
	public ResponseEntity<Order> addOrderPosition(@RequestBody OrderPosition orderPosition) {

		final Pizza pizza = pizzaDAO.get(orderPosition.getPizzaID());
		if (pizza == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find pizza with given pizzaID");
		}

		final Order order = orderDAO.get(orderPosition.getOrderID());
		if (order == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find order with given orderID");
		}

		if (orderPosition.getCount() < 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Count cannot be negative");
		}

		order.setTotalPrice(order.getTotalPrice() + pizza.getPrice() * orderPosition.getCount());
		orderPositionDAO.insert(orderPosition);

		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Order>> getAll() {
		return new ResponseEntity<>(orderDAO.getAll(), HttpStatus.OK);
	}

	@GetMapping("/paid")
	public ResponseEntity<List<Order>> getAllPaid() {
		return new ResponseEntity<>(
				orderDAO.getAll().stream().filter(order -> PAID == order.getStatus()).collect(Collectors.toList()),
				HttpStatus.OK);
	}

	@PutMapping("/status")
	public ResponseEntity<Order> changeStatus(@RequestBody Order fakeOrder) {
		if (fakeOrder.getId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID mustn't be null!");
		}
		final Order order = orderDAO.get(fakeOrder.getId());
		if (order == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find order with given orderID");
		}

		order.setStatus(fakeOrder.getStatus());
		return new ResponseEntity<>(order, HttpStatus.OK);
	}

}

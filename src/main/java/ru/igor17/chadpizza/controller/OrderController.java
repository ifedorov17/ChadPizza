package ru.igor17.chadpizza.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ru.igor17.chadpizza.service.OrderService;
import ru.igor17.chadpizza.view.OrderAddDTO;
import ru.igor17.chadpizza.view.OrderChangeStatusDTO;
import ru.igor17.chadpizza.view.OrderListDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/order")
public class OrderController {

	private final OrderService orderService;

	@PostMapping
	public ResponseEntity<OrderListDTO> create(@RequestBody OrderAddDTO orderAddDTO) {
		if (!orderService.canOrderBeApplied(orderAddDTO)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Order cannot be applied. No ingredients!");
		}
		final OrderListDTO order = orderService.createEntity(orderAddDTO);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderListDTO> get(@PathVariable final Long id) {
		final OrderListDTO order = orderService.getById(id);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<OrderListDTO>> getAll() {
		return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/paid")
	public ResponseEntity<List<OrderListDTO>> getAllPaid() {
		return new ResponseEntity<>(orderService.getAllPaid(), HttpStatus.OK);
	}

	@PutMapping("/status")
	public ResponseEntity<OrderListDTO> changeStatus(@RequestBody OrderChangeStatusDTO changeStatusDTO) {
		if (changeStatusDTO.getOrderId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID mustn't be null!");
		}
		final OrderListDTO order = orderService.changeStatus(changeStatusDTO);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<OrderListDTO> deleteOrder(@PathVariable final Long id) {
		if (id == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID mustn't be null!");
		}
		orderService.deleteEntity(id);
		return new ResponseEntity<>(new OrderListDTO(), HttpStatus.OK);
	}

}

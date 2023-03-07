package ru.igor17.chadpizza.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ru.igor17.chadpizza.dao.CustomerDAO;
import ru.igor17.chadpizza.model.Customer;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

	private final CustomerDAO customerDAO;

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping
	public ResponseEntity<List<Customer>> getAll() {
		return new ResponseEntity<>(customerDAO.getAll(), HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/{id}")
	public ResponseEntity<Customer> get(@PathVariable final Long id) {
		Customer customer = customerDAO.get(id);
		if (customer == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping
	public ResponseEntity<Customer> create(@RequestBody final Customer customer) {
		customerDAO.insert(customer);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping("/{id}")
	public ResponseEntity<Customer> update(@PathVariable final Long id, @RequestBody final Customer customer) {
		if (id == null || customerDAO.get(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
		customerDAO.update(customer);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

}

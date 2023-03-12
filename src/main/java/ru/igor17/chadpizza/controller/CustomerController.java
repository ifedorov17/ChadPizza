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
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/customer")
public class CustomerController {
//MAY BE DEPRECATED IN FURTHER VERSIONS
	private final CustomerDAO customerDAO;

	@GetMapping
	public ResponseEntity<List<Customer>> getAll() {
		return new ResponseEntity<>(customerDAO.getAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> get(@PathVariable final Long id) {
		Customer customer = customerDAO.getById(id);
		if (customer == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}


	@PostMapping
	public ResponseEntity<Customer> create(@RequestBody final Customer customer) {
		customerDAO.insert(customer);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}


	@PutMapping("/{id}")
	public ResponseEntity<Customer> update(@PathVariable final Long id, @RequestBody final Customer customer) {
		if (id == null || customerDAO.getById(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
		customerDAO.update(customer);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

}

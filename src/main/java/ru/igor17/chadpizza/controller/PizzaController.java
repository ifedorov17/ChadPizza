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

import ru.igor17.chadpizza.dao.PizzaDAO;

import ru.igor17.chadpizza.model.Pizza;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/pizza")
public class PizzaController {

	private final PizzaDAO pizzaDAO;

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping
	public ResponseEntity<List<Pizza>> getAll() {
		return new ResponseEntity<>(pizzaDAO.getAll(), HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/{id}")
	public ResponseEntity<Pizza> get(@PathVariable final Long id) {
		Pizza pizza = pizzaDAO.get(id);
		if (pizza == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping
	public ResponseEntity<Pizza> create(@RequestBody final Pizza pizza) {
		pizzaDAO.insert(pizza);
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping("/{id}")
	public ResponseEntity<Pizza> update(@PathVariable final Long id, @RequestBody final Pizza pizza) {
		if (pizzaDAO.get(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
		pizzaDAO.update(pizza);
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}

}

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

import ru.igor17.chadpizza.service.PizzaService;
import ru.igor17.chadpizza.view.PizzaDTO;

import java.util.List;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/pizza")
public class PizzaController {

	private final PizzaService pizzaService;

	@GetMapping
	public ResponseEntity<List<PizzaDTO>> getAll() {
		return new ResponseEntity<>(pizzaService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PizzaDTO> get(@PathVariable final Long id) {
		return new ResponseEntity<>(pizzaService.getById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<PizzaDTO> create(@RequestBody final PizzaDTO pizzaDTO) {
		return new ResponseEntity<>(pizzaService.createEntity(pizzaDTO), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<PizzaDTO> update(@RequestBody final PizzaDTO pizzaDTO) {
		return new ResponseEntity<>(pizzaService.updateEntity(pizzaDTO), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<PizzaDTO> delete(@PathVariable final Long id) {
		pizzaService.deleteEntity(id);
		return new ResponseEntity<>(new PizzaDTO(), HttpStatus.OK);
	}

}

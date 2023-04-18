package ru.igor17.chadpizza.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.igor17.chadpizza.service.IngredientService;
import ru.igor17.chadpizza.view.IngredientDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/ingredient")
public class IngredientController {

	private final IngredientService service;

	@GetMapping
	public ResponseEntity<List<IngredientDTO>> getAll() {
		return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<List<IngredientDTO>> changeCount(@RequestBody final List<IngredientDTO> ingredients) {
		ingredients.forEach(service::updateEntity);
		return new ResponseEntity<>(ingredients, HttpStatus.OK);
	}

}

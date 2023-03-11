package ru.igor17.chadpizza.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ru.igor17.chadpizza.dao.PizzaDAO;
import ru.igor17.chadpizza.model.Pizza;
import ru.igor17.chadpizza.view.PizzaDTO;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class PizzaService {

	private final PizzaDAO pizzaDAO;

	private Pizza dtoToEntity(final PizzaDTO dto) {
		final Pizza pizza = new Pizza();

		pizza.setName(dto.getName());
		pizza.setDescription(dto.getDescription());
		pizza.setPrice(Float.parseFloat(dto.getPrice()));

		return pizza;
	}

	private PizzaDTO entityToDto(final Pizza pizza) {
		final PizzaDTO dto = new PizzaDTO();

		dto.setName(pizza.getName());
		dto.setDescription(pizza.getDescription());
		dto.setPrice(pizza.getPrice().toString());

		return dto;
	}

}

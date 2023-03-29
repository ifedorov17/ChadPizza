package ru.igor17.chadpizza.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ru.igor17.chadpizza.dao.PizzaDAO;
import ru.igor17.chadpizza.model.Pizza;
import ru.igor17.chadpizza.view.PizzaDTO;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class PizzaService implements IBaseService<Pizza, PizzaDTO> {

	private final PizzaDAO pizzaDAO;

	@Override
	public List<PizzaDTO> getAll() {
		return pizzaDAO.getAll().stream()
				.map(this::entityToDto)
				.toList();
	}

	@Override
	public PizzaDTO getById(final Long id) {
		return entityToDto(pizzaDAO.getById(id));
	}

	@Override
	public Pizza createEntity(final PizzaDTO dto) {
		final Pizza pizza = dtoToEntity(dto);
		pizzaDAO.insert(pizza);
		return pizza;
	}

	@Override
	public Pizza updateEntity(final PizzaDTO dto) {
		final Pizza pizza = pizzaDAO.getById(dto.getId());
		pizza.setPrice(Float.parseFloat(dto.getPrice()));
		pizza.setName(dto.getName());
		pizza.setDescription(dto.getDescription());
		pizzaDAO.update(pizza);
		return pizza;
	}

	@Override
	public void deleteEntity(Long id) {
		pizzaDAO.deleteById(id);
	}

	private Pizza dtoToEntity(final PizzaDTO dto) {
		final Pizza pizza = new Pizza();

		pizza.setName(dto.getName());
		pizza.setDescription(dto.getDescription());
		pizza.setPrice(Float.parseFloat(dto.getPrice()));
		pizza.setPictureUrl(dto.getPictureUrl());

		return pizza;
	}

	private PizzaDTO entityToDto(final Pizza pizza) {
		final PizzaDTO dto = new PizzaDTO();

		dto.setId(pizza.getId());
		dto.setName(pizza.getName());
		dto.setDescription(pizza.getDescription());
		dto.setPrice(pizza.getPrice().toString());
		dto.setPictureUrl(pizza.getPictureUrl());

		return dto;
	}

}

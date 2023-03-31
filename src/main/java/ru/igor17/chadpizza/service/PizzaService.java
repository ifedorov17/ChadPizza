package ru.igor17.chadpizza.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ru.igor17.chadpizza.model.Pizza;
import ru.igor17.chadpizza.repository.IPizzaRepository;
import ru.igor17.chadpizza.view.PizzaDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class PizzaService implements IBaseService<Pizza, PizzaDTO> {

	private final IPizzaRepository pizzaRepository;

	@Override
	public List<PizzaDTO> getAll() {
		return StreamSupport.stream(pizzaRepository.findAll().spliterator(), false)
				.map(this::entityToDto)
				.collect(Collectors.toList());
	}

	@Override
	public PizzaDTO getById(final Long id) {
		return pizzaRepository.findById(id).map(this::entityToDto).orElse(null);
	}

	@Override
	public Pizza createEntity(final PizzaDTO dto) {
		final Pizza pizza = dtoToEntity(dto);
		pizzaRepository.save(pizza);
		return pizza;
	}

	@Override
	public Pizza updateEntity(final PizzaDTO dto) {

		return pizzaRepository.findById(dto.getId())
						.map(pizza -> {
							pizza.setPrice(Float.parseFloat(dto.getPrice()));
							pizza.setName(dto.getName());
							pizza.setDescription(dto.getDescription());
							return pizzaRepository.save(pizza);
						}).orElse(null);
	}

	@Override
	public void deleteEntity(Long id) {
		pizzaRepository.deleteById(id);
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

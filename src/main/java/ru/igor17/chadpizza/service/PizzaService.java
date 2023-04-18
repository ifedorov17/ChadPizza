package ru.igor17.chadpizza.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ru.igor17.chadpizza.model.LnkPizzaIngredient;
import ru.igor17.chadpizza.model.Pizza;
import ru.igor17.chadpizza.repository.ILnkPizzaIngredientRepository;
import ru.igor17.chadpizza.repository.IPizzaRepository;
import ru.igor17.chadpizza.view.PizzaDTO;

import java.util.List;
import java.util.stream.StreamSupport;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class PizzaService implements IBaseService<Pizza, PizzaDTO> {

	private final IPizzaRepository pizzaRepository;

	private final ILnkPizzaIngredientRepository lnkPizzaIngredientRepository;

	@Override
	public List<PizzaDTO> getAll() {
		 return StreamSupport.stream(pizzaRepository.findAll().spliterator(), false)
				.map(this::entityToDto)
				.map(this::setIsAvailable)
				.toList();
	}

	@Override
	public PizzaDTO getById(final Long id) {
		return pizzaRepository.findById(id).map(this::entityToDto).orElse(null);
	}

	@Override
	public PizzaDTO createEntity(final PizzaDTO dto) {
		final Pizza pizza = dtoToEntity(dto);
		pizzaRepository.save(pizza);
		return entityToDto(pizza);
	}

	@Override
	public PizzaDTO updateEntity(final PizzaDTO dto) {

		return pizzaRepository.findById(dto.getId())
						.map(pizza -> {
							pizza.setPrice(Float.parseFloat(dto.getPrice()));
							pizza.setName(dto.getName());
							pizza.setDescription(dto.getDescription());
							return pizzaRepository.save(pizza);
						}).map(this::entityToDto).orElse(null);
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

	private PizzaDTO setIsAvailable(final PizzaDTO pizzaDTO) {
		pizzaDTO.setIsAvailable(
				lnkPizzaIngredientRepository.findLnkPizzaIngredientsByPizzaId(pizzaDTO.getId())
						.stream()
						.map(LnkPizzaIngredient::getIngredient)
						.allMatch(ingredient -> ingredient.getCount() > 0)
		);
		return pizzaDTO;
	}
}

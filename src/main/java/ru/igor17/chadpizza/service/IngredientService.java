package ru.igor17.chadpizza.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ru.igor17.chadpizza.model.Ingredient;
import ru.igor17.chadpizza.repository.IIngredientRepository;
import ru.igor17.chadpizza.view.IngredientDTO;

import java.util.List;
import java.util.stream.StreamSupport;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class IngredientService implements IBaseService<Ingredient, IngredientDTO> {

	private final IIngredientRepository ingredientRepository;
	@Override
	public IngredientDTO getById(Long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<IngredientDTO> getAll() {
		return StreamSupport.stream(ingredientRepository.findAll().spliterator(), false)
				.map(this::entityToDTO).toList();
	}

	@Override
	public Ingredient createEntity(IngredientDTO dto) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Ingredient updateEntity(IngredientDTO dto) {
		final Ingredient ingredient = ingredientRepository.findByName(dto.getName());
		ingredient.setCount(dto.getCount());
		ingredientRepository.save(ingredient);
		return ingredient;
	}

	@Override
	public void deleteEntity(Long id) {
		throw new UnsupportedOperationException();
	}

	private Ingredient dtoToEntity(final IngredientDTO dto) {
		 return Ingredient.builder()
				 .count(dto.getCount())
				 .name(dto.getName())
				 .build();
	}

	private IngredientDTO entityToDTO(final Ingredient ingredient) {
		return IngredientDTO.builder()
				.count(ingredient.getCount())
				.name(ingredient.getName())
				.build();
	}
}

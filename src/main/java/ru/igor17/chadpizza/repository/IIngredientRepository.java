package ru.igor17.chadpizza.repository;

import org.springframework.stereotype.Repository;
import ru.igor17.chadpizza.model.Ingredient;

@Repository
public interface IIngredientRepository extends IBaseRepository<Ingredient> {

	Ingredient findByName(final String name);

}

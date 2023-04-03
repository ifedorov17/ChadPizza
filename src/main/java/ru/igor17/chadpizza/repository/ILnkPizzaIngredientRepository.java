package ru.igor17.chadpizza.repository;

import org.springframework.stereotype.Repository;
import ru.igor17.chadpizza.model.LnkPizzaIngredient;

import java.util.List;

@Repository
public interface ILnkPizzaIngredientRepository extends IBaseRepository<LnkPizzaIngredient> {

	List<LnkPizzaIngredient> findLnkPizzaIngredientsByPizzaId(Long pizzaId);

}

package ru.igor17.chadpizza.repository;

import org.springframework.stereotype.Repository;
import ru.igor17.chadpizza.model.Pizza;

import java.util.Optional;

@Repository
public interface IPizzaRepository extends IBaseRepository<Pizza> {

	public Pizza findByName(final String name);

}

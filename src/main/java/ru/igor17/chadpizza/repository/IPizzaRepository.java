package ru.igor17.chadpizza.repository;

import org.springframework.stereotype.Repository;
import ru.igor17.chadpizza.model.Pizza;

@Repository
public interface IPizzaRepository extends IBaseRepository<Pizza> {

	Pizza findByName(final String name);

}

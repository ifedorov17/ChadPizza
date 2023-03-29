package ru.igor17.chadpizza.dao;

import org.springframework.stereotype.Repository;
import ru.igor17.chadpizza.model.Pizza;

@Repository
public class PizzaDAO extends BaseDAO<Pizza> {

	PizzaDAO() {
		super(Pizza.class);
	}
}

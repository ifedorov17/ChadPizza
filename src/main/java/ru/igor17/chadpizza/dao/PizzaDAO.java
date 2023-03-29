package ru.igor17.chadpizza.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import ru.igor17.chadpizza.model.Pizza;

@Repository
public class PizzaDAO extends BaseDAO<Pizza> {

	PizzaDAO() {
		super(Pizza.class);
	}

	public Pizza getPizzaByName(final String pizzaName) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Pizza> cq = cb.createQuery(Pizza.class);
		Root<Pizza> root = cq.from(Pizza.class);
		cq.select(root);
		cq.where(cb.equal(root.get("name"), pizzaName));

		return entityManager.createQuery(cq).getSingleResult();
	}
}

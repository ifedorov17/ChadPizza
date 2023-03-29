package ru.igor17.chadpizza.dao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import ru.igor17.chadpizza.model.Customer;

import java.util.List;


@Repository
public class CustomerDAO extends BaseDAO<Customer> {

	CustomerDAO() {
		super(Customer.class);
	}

	@Override
	public List<Customer> getAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
		Root<Customer> root = cq.from(Customer.class);
		cq.select(root);
		return entityManager.createQuery(cq).getResultList();
	}
}

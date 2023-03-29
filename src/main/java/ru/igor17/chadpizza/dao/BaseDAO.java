package ru.igor17.chadpizza.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import ru.igor17.chadpizza.model.BaseModel;

import java.util.List;

@Transactional
public abstract class BaseDAO<T extends BaseModel>{

	@PersistenceContext
	protected EntityManager entityManager;

	private final Class<T> type;

	BaseDAO(Class<T> type) {
		this.type = type;
	}
	public T getById(Long id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(type);
		Root<T> root = cq.from(type);
		cq.select(root);
		cq.where(cb.equal(root.get("id"), id));

		return entityManager.createQuery(cq).getSingleResult();
	}

	public List<T> getAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(type);
		Root<T> root = cq.from(type);
		cq.select(root);
		return entityManager.createQuery(cq).getResultList();
	}

	public void insert(T model) {
		entityManager.persist(model);
		entityManager.flush();
	}

	public void update(T model) {
		entityManager.merge(model);
		entityManager.flush();
	}

}

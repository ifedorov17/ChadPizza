package ru.igor17.chadpizza.dao;

import ru.igor17.chadpizza.model.BaseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BaseDAO<T extends BaseModel>{

	protected ConcurrentHashMap<Long, T> db = new ConcurrentHashMap<>();

	public void insert(T model) {
		model.setId(generateID());
		db.put(model.getId(), model);
	}

	public void update(T model) {
		db.put(model.getId(), model);
	}

	public T get(Long id) {
		return db.get(id);
	}

	public List<T> getAll() {
		return new ArrayList<>(db.values());
	}

	private Long generateID() {
		return this.db.keySet().stream().max(Long::compareTo).map(l -> l += 1).orElse(1L);
	}

}

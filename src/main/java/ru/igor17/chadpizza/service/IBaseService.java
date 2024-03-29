package ru.igor17.chadpizza.service;

import ru.igor17.chadpizza.model.BaseModel;
import ru.igor17.chadpizza.view.BaseDTO;

import java.util.List;

public interface IBaseService<E extends BaseModel, T extends BaseDTO> {

	T getById(final Long id);

	List<T> getAll();

	T createEntity(final T dto);

	T updateEntity(final T dto);

	void deleteEntity(final Long id);

}

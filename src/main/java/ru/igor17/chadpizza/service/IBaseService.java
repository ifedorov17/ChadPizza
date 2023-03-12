package ru.igor17.chadpizza.service;

import ru.igor17.chadpizza.model.BaseModel;
import ru.igor17.chadpizza.view.BaseDTO;

import java.util.List;

public interface IBaseService<E extends BaseModel, T extends BaseDTO> {

	public T getById(final Long id);

	public List<T> getAll();

	public E createEntity(final T dto);

	public E updateEntity(final T dto);

}

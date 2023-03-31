package ru.igor17.chadpizza.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.igor17.chadpizza.model.BaseModel;

@Transactional
@Repository
public interface IBaseRepository<T extends BaseModel> extends CrudRepository<T, Long> {
}

package ru.igor17.chadpizza.repository;

import org.springframework.stereotype.Repository;
import ru.igor17.chadpizza.model.Order;
import ru.igor17.chadpizza.model.Status;

import java.util.List;

@Repository
public interface IOrderRepository extends IBaseRepository<Order> {

	List<Order> findAllByStatus(final Status status);

}

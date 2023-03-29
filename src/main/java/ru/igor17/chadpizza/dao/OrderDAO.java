package ru.igor17.chadpizza.dao;

import org.springframework.stereotype.Repository;
import ru.igor17.chadpizza.model.Order;

@Repository
public class OrderDAO extends BaseDAO<Order> {

	OrderDAO() {
		super(Order.class);
	}
}

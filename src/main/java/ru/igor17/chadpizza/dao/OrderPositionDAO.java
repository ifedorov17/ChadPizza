package ru.igor17.chadpizza.dao;

import org.springframework.stereotype.Repository;
import ru.igor17.chadpizza.model.OrderPosition;


@Repository
public class OrderPositionDAO extends BaseDAO<OrderPosition> {

	OrderPositionDAO() {
		super(OrderPosition.class);
	}
}

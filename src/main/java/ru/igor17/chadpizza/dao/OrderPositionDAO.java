package ru.igor17.chadpizza.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.igor17.chadpizza.model.OrderPosition;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderPositionDAO extends BaseDAO<OrderPosition> {

	public List<OrderPosition> getOrderPositionsByOrderId(final Long orderID) {
		return db.values().stream()
				.filter(orderPosition -> orderPosition.getOrderID().equals(orderID))
				.toList();
	}

}

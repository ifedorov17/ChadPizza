package ru.igor17.chadpizza.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.igor17.chadpizza.model.OrderPosition;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class OrderPositionDAO extends BaseDAO<OrderPosition> {

	public List<OrderPosition> getOrderPositionsByOrderId(final Long orderID) {
		return db.values().stream()
				.filter(orderPosition -> orderPosition.getOrderID().equals(orderID))
				.toList();
	}

	OrderPositionDAO() {
		this.db = new ConcurrentHashMap<>();

		//Mocks for frontend testing
		OrderPosition op1 = new OrderPosition();
		op1.setId(1L);
		op1.setOrderID(1L);
		op1.setPizzaID(1L);
		op1.setCount(4);

		OrderPosition op2 = new OrderPosition();
		op2.setId(2L);
		op2.setOrderID(1L);
		op2.setPizzaID(2L);
		op2.setCount(2);

		db.put(1L, op1);
		db.put(2L, op2);


		OrderPosition op3 = new OrderPosition();
		op3.setId(3L);
		op3.setOrderID(2L);
		op3.setPizzaID(2L);
		op3.setCount(1);

		db.put(3L, op3);
	}

}

package ru.igor17.chadpizza.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.igor17.chadpizza.model.Order;
import ru.igor17.chadpizza.model.OrderPosition;
import ru.igor17.chadpizza.model.Pizza;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class OrderPositionDAO extends BaseDAO<OrderPosition> {

	public List<OrderPosition> getOrderPositionsByOrderId(final Long orderID) {
		return db.values().stream()
				.filter(orderPosition -> orderPosition.getOrder().getId().equals(orderID))
				.toList();
	}

	OrderPositionDAO() {
		this.db = new ConcurrentHashMap<>();

		Order order1 = new Order();
		order1.setId(1L);

		Order order2 = new Order();
		order2.setId(2L);

		Pizza pizza1 = new Pizza();
		pizza1.setId(1L);
		pizza1.setPrice(12.0f);
		pizza1.setName("pipa");

		Pizza pizza2 = new Pizza();
		pizza2.setId(2L);
		pizza2.setPrice(1222.0f);
		pizza2.setName("pipa2");


		//Mocks for frontend testing
		OrderPosition op1 = new OrderPosition();
		op1.setId(1L);
		op1.setOrder(order1);
		op1.setPizza(pizza1);
		op1.setCount(4);

		OrderPosition op2 = new OrderPosition();
		op2.setId(2L);
		op2.setOrder(order1);
		op2.setPizza(pizza2);
		op2.setCount(2);

		db.put(1L, op1);
		db.put(2L, op2);


		OrderPosition op3 = new OrderPosition();
		op3.setId(3L);
		op3.setOrder(order2);
		op3.setPizza(pizza2);
		op3.setCount(1);

		db.put(3L, op3);
	}

}

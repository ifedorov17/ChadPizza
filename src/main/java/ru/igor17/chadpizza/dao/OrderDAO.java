package ru.igor17.chadpizza.dao;

import org.springframework.stereotype.Component;
import ru.igor17.chadpizza.model.Order;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

import static ru.igor17.chadpizza.model.Status.DRAFT;
import static ru.igor17.chadpizza.model.Status.PAID;

@Component
public class OrderDAO extends BaseDAO<Order> {
	OrderDAO() {
		this.db = new ConcurrentHashMap<>();

		//Mocks for frontend testing
		Order order1 = new Order();
		order1.setUserID(1L);
		order1.setStatus(PAID);
		order1.setTotalPrice(2000.0f);
		order1.setOrderDateTime(LocalDateTime.now());
		order1.setId(1L);
		db.put(1L, order1);

		Order order2 = new Order();
		order2.setUserID(2L);
		order2.setStatus(DRAFT);
		order2.setTotalPrice(400.0f);
		order2.setOrderDateTime(LocalDateTime.now());
		order2.setId(2L);
		db.put(2L, order2);

	}
}

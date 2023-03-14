package ru.igor17.chadpizza.dao;
import org.springframework.stereotype.Component;
import ru.igor17.chadpizza.model.Customer;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class CustomerDAO extends BaseDAO<Customer> {

	CustomerDAO() {
		this.db = new ConcurrentHashMap<>();

		Customer customer1 = new Customer();
		customer1.setId(1L);
		customer1.setFirstName("Эндрю");
		customer1.setSurname("Тейт");
		customer1.setMiddleName("");
		customer1.setAddress("ул. Пушкина, д.1");
		customer1.setPhoneNumber("8-800-555-3535");
		db.put(1L, customer1);

		Customer customer2 = new Customer();
		customer2.setId(2L);
		customer2.setFirstName("Тристан");
		customer2.setSurname("Тейт");
		customer2.setMiddleName("");
		customer2.setAddress("бул. Толстого, д.144");
		customer2.setPhoneNumber("8-919-099-1337");
		db.put(2L, customer2);
	}

}

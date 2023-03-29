package ru.igor17.chadpizza.dao;
import org.springframework.stereotype.Repository;
import ru.igor17.chadpizza.model.Customer;


@Repository
public class CustomerDAO extends BaseDAO<Customer> {

	CustomerDAO() {
		super(Customer.class);
	}
}

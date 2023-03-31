package ru.igor17.chadpizza.repository;

import org.springframework.stereotype.Repository;
import ru.igor17.chadpizza.model.Customer;

@Repository
public interface ICustomerRepository extends IBaseRepository<Customer> {
}

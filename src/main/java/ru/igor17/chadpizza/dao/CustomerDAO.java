package ru.igor17.chadpizza.dao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.igor17.chadpizza.model.Customer;

@Component
@RequiredArgsConstructor
public class CustomerDAO extends BaseDAO<Customer> {

}

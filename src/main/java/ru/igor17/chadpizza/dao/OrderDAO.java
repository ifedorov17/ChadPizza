package ru.igor17.chadpizza.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.igor17.chadpizza.model.Order;

@Component
@RequiredArgsConstructor
public class OrderDAO extends BaseDAO<Order> {

}

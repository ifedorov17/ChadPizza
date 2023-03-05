package ru.igor17.chadpizza.dao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.igor17.chadpizza.model.Pizza;

@Component
@RequiredArgsConstructor
public class PizzaDAO extends BaseDAO<Pizza> {
}

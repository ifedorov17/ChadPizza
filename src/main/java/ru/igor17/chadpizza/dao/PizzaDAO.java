package ru.igor17.chadpizza.dao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.igor17.chadpizza.model.Pizza;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PizzaDAO extends BaseDAO<Pizza> {

	public Pizza getPizzaByName(final String name) {
		 return db.values().stream()
				.filter(pizza -> pizza.getName().equals(name))
				.findFirst()
				 .orElseThrow(() -> new RuntimeException("No pizza fpund with name: " + name));
	}

}

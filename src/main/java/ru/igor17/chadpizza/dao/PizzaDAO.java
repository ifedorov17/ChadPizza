package ru.igor17.chadpizza.dao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.igor17.chadpizza.model.Pizza;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PizzaDAO extends BaseDAO<Pizza> {

	public Pizza getPizzaByName(final String name) {
		 return db.values().stream()
				.filter(pizza -> pizza.getName().equals(name))
				.findFirst()
				 .orElseThrow(() -> new RuntimeException("No pizza fpund with name: " + name));
	}

	PizzaDAO() {
		this.db = new ConcurrentHashMap<>();

		Pizza margarita = new Pizza();
		margarita.setName("Маргарита");
		margarita.setId(1L);
		margarita.setDescription(
				"Традиционное итальянское блюдо, известное во всем мире. \n Состав: тесто, томатный соус, сыр моцарелла."
		);
		margarita.setPrice(350.0f);

		db.put(1L, margarita);

		Pizza pepperoni = new Pizza();
		pepperoni.setName("Пепперони");
		pepperoni.setId(2L);
		pepperoni.setDescription(
				"Классическая пицца Пепперони названа в честь уникальной по своим вкусовым качествам острой салями " +
						"Pepperoni, которая является основным ингредиентом и задает главную вкусовую нотку" +
						"с перчинкой.\n Состав: тесто, томатный соус, сыр моцарелла, колбаса пепперони."
		);
		pepperoni.setPrice(400.0f);

		db.put(2L, pepperoni);
	}

}

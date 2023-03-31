package ru.igor17.chadpizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "ru.igor17.chadpizza.repository")
public class ChadPizzaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChadPizzaApplication.class, args);
	}

}

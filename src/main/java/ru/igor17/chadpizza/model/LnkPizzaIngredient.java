package ru.igor17.chadpizza.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "LNK_PIZZA_INGREDIENT")
public class LnkPizzaIngredient extends BaseModel {

	@ManyToOne
	@JoinColumn(name = "PIZZA_ID")
	private Pizza pizza;

	@ManyToOne
	@JoinColumn(name = "INGREDIENT_ID")
	private Ingredient ingredient;

	@Positive
	@Column(name = "COUNT")
	private Integer count;

}

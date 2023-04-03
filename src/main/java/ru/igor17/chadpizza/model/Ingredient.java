package ru.igor17.chadpizza.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "INGREDIENT")
public class Ingredient extends BaseModel {

	@Column(name = "NAME")
	private String name;

	@Column(name = "COUNT")
	private Integer count;

}

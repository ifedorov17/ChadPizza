package ru.igor17.chadpizza.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "ORDER_POSITION")
public class OrderPosition extends BaseModel {

	@ManyToOne
	@JoinColumn(name = "ORDER_ID")
	private Order order;

	@ManyToOne
	@JoinColumn(name = "PIZZA_ID")
	private Pizza pizza;

	@Positive
	@Column(name = "COUNT")
	private Integer count;

}

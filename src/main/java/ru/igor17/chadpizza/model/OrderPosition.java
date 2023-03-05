package ru.igor17.chadpizza.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;

@Getter
@Setter
@RequiredArgsConstructor
public class OrderPosition extends BaseModel {

	private Long orderID;

	private Long pizzaID;

	@Positive
	private Integer count;

}

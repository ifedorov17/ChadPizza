package ru.igor17.chadpizza.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderPositionDTO extends BaseDTO {

	private String pizzaName;

	private Integer count;

}

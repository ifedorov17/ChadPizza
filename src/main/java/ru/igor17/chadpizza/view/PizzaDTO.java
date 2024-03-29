package ru.igor17.chadpizza.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PizzaDTO extends BaseDTO {

	private Long id;

	private String name;

	private String description;

	private String price;

	private String pictureUrl;

	private Boolean isAvailable;

}

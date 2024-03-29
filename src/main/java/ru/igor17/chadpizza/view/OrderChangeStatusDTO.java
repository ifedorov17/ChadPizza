package ru.igor17.chadpizza.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderChangeStatusDTO extends BaseDTO {

	private Long orderId;

	private String status;

}

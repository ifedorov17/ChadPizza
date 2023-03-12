package ru.igor17.chadpizza.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderListDTO extends BaseDTO {

	private String customerFIO;

	private String customerPhoneNumber;

	private String customerAddress;

	private String orderDateTime;

	private String orderStatus;

	private String orderTotalPrice;

	private List<OrderPositionDTO> orderPositions;

}

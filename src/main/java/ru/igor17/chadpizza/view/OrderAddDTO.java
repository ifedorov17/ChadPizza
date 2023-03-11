package ru.igor17.chadpizza.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderAddDTO {

	private String customerFIO;

	private String customerPhoneNumber;

	private String customerAddress;

	private String orderTotalPrice;

	private List<OrderPositionDTO> orderPositions;

}

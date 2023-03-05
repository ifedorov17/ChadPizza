package ru.igor17.chadpizza.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class Order extends BaseModel {

	private Long userID;

	private LocalDateTime orderDateTime;

	private Status status;

	private Float totalPrice;

}

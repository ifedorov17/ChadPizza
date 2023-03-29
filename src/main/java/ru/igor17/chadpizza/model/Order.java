package ru.igor17.chadpizza.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "ORDER")
public class Order extends BaseModel {

	@OneToOne
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;

	@Column(name = "ORDER_DATE_TIME")
	private LocalDateTime orderDateTime;

	@Column(name = "STATUS", nullable = false)
	private Status status;

	@Column(name = "TOTAL_PRICE")
	private Float totalPrice;

}

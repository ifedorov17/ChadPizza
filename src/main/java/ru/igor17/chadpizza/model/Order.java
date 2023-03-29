package ru.igor17.chadpizza.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "ORDERS")
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

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "OP_ID")
	private List<OrderPosition> orderPositions;

}

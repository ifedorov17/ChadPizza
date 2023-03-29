package ru.igor17.chadpizza.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "PIZZA")
public class Pizza extends BaseModel {

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Positive
	@Column(name = "PRICE")
	private Float price;

	@Column(name = "PICTURE_URL")
	private String pictureUrl;

}

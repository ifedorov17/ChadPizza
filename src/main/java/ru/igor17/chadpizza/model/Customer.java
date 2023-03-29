package ru.igor17.chadpizza.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "CUSTOMER")
public class Customer extends BaseModel {

	@Column(name = "SURNAME")
	private String surname;

	@Column(name = "FIRSTNAME")
	private String firstName;

	@Column(name = "MIDDLENAME")
	private String middleName;

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@Column(name = "ADDRESS")
	private String address;

}

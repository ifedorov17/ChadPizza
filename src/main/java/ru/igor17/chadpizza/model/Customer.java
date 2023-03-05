package ru.igor17.chadpizza.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Customer extends BaseModel {

	private String surname;

	private String firstName;

	private String middleName;

	private String phoneNumber;

	private String address;

}

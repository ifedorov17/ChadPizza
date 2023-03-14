package ru.igor17.chadpizza.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Pizza extends BaseModel {

	private String name;

	private String description;

	private Float price;

	private String pictureUrl;

}

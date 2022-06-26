package com.play.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

	private long id;

	private String name;

	private String brand;

	private int price;

	private CartDTO cartDto;

}
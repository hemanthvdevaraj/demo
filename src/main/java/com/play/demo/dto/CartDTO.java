package com.play.demo.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDTO {

	private long id;

	private UserInfoDTO userInfo;

	private List<ProductDTO> products;

}
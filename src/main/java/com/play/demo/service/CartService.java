package com.play.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.play.demo.component.CartComponent;
import com.play.demo.dto.CartDTO;

@Service
public class CartService {

	@Autowired
	private CartComponent cartComp;

	public CartDTO loadCart(long userId) {

		CartDTO cartDto = cartComp.loadCart(userId);

		return cartDto;
	}

	public CartDTO addToCart(long userId, long productId) {

		CartDTO cartDto = cartComp.addToCart(userId, productId);

		return cartDto;
	}
}

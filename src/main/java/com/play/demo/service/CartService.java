package com.play.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.play.demo.component.CartComponent;
import com.play.demo.entity.Cart;

@Service
public class CartService {

	@Autowired
	private CartComponent cartComp;

	public Cart loadCart(long userId) {

		Cart cart = cartComp.loadCart(userId);

		return cart;
	}

	public Cart addToCart(long userId, long productId) {

		Cart cart = cartComp.addToCart(userId, productId);

		return cart;
	}
}

package com.play.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.play.demo.entity.Cart;
import com.play.demo.service.CartService;

@RestController
public class CartController {

	@Autowired
	private CartService cartService;

	@GetMapping("loadCart")
	public Cart loadCart(@RequestParam long userId) {

		Cart cart = cartService.loadCart(userId);

		return cart;
	}

	@GetMapping("addToCart")
	public Cart addToCart(@RequestParam long userId, @RequestParam long productId) {

		Cart cart = cartService.addToCart(userId, productId);

		return cart;
	}
}

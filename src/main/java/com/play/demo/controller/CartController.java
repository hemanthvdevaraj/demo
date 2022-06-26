package com.play.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.play.demo.dto.CartDTO;
import com.play.demo.service.CartService;

@RestController
public class CartController {

	@Autowired
	private CartService cartService;

	@GetMapping("loadCart")
	public CartDTO loadCart(@RequestParam long userId) {

		CartDTO cartDto = cartService.loadCart(userId);

		return cartDto;
	}

	@GetMapping("addToCart")
	public CartDTO addToCart(@RequestParam long userId, @RequestParam long productId) {

		CartDTO cartDto = cartService.addToCart(userId, productId);

		return cartDto;
	}
}

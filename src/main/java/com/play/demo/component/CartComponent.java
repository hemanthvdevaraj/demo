package com.play.demo.component;

import com.play.demo.entity.Cart;

public interface CartComponent {

	public Cart addToCart(long userId, long productId);

	public Cart loadCart(long userId);
}

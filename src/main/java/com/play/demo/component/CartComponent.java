package com.play.demo.component;

import com.play.demo.dto.CartDTO;

public interface CartComponent {

	public CartDTO addToCart(long userId, long productId);

	public CartDTO loadCart(long userId);
}

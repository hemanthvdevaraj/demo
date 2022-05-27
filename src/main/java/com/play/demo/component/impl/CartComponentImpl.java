package com.play.demo.component.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.play.demo.component.CartComponent;
import com.play.demo.entity.Cart;
import com.play.demo.entity.Product;
import com.play.demo.entity.UserInfo;
import com.play.demo.repository.CartRepository;
import com.play.demo.repository.ProductRepository;
import com.play.demo.repository.UserInfoRepository;

@Component
public class CartComponentImpl implements CartComponent {

	@Autowired
	CartRepository cartRepository;

	@Autowired
	UserInfoRepository userInfoRepository;

	@Autowired
	ProductRepository productRepository;

	@Override
	public Cart addToCart(long userId, long productId) {

		Optional<UserInfo> userInfoOptnl = userInfoRepository.findById(userId);

		UserInfo userInfo = userInfoOptnl.get();

		Optional<Product> productOptnl = productRepository.findById(productId);

		Product product = productOptnl.get();

		Cart cart = new Cart();
		cart.setProduct(product);
		cart.setUserInfo(userInfo);

		return cartRepository.save(cart);
	}

	@Override
	public Cart loadCart(long userId) {

		List<Cart> cartList = cartRepository.loadCart(userId);

		return cartList.get(0);
	}
}

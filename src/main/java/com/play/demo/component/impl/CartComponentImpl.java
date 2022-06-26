package com.play.demo.component.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.play.demo.component.CartComponent;
import com.play.demo.dto.CartDTO;
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

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CartDTO addToCart(long userId, long productId) {

		Optional<UserInfo> userInfoOptnl = userInfoRepository.findById(userId);

		UserInfo userInfo = userInfoOptnl.get();

		Cart cart = cartRepository.loadCart(userId);

		if (cart == null) {
			cart = new Cart();
			cart.setUserInfo(userInfo);
		}

		Optional<Product> productOptnl = productRepository.findById(productId);

		Product product = productOptnl.get();

		List<Product> products = cart.getProducts();

		if (CollectionUtils.isEmpty(products)) {
			products = new ArrayList<>();
		}

		product.setCart(cart);

		products.add(product);

		cart.setProducts(products);
		cart.setUserInfo(userInfo);

		cart = cartRepository.save(cart);

		return convertToDTO(cart);
	}

	@Override
	public CartDTO loadCart(long userId) {

		Cart cart = cartRepository.loadCart(userId);

		return convertToDTO(cart);
	}

	private CartDTO convertToDTO(Cart cart) {
		return modelMapper.map(cart, CartDTO.class);
	}
}

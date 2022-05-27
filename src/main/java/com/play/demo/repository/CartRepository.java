package com.play.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.play.demo.entity.Cart;

public interface CartRepository extends CrudRepository<Cart, Long> {

	@Query(value = "SELECT c FROM Cart c WHERE c.userInfo.id = ?1")
	public List<Cart> loadCart(long userId);

}

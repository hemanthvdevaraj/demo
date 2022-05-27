package com.play.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.play.demo.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}

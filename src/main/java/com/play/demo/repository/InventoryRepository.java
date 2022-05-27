package com.play.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.play.demo.entity.Inventory;

public interface InventoryRepository extends CrudRepository<Inventory, Long> {

}

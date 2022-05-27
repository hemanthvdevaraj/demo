package com.play.demo.component.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.play.demo.component.InventoryComponent;
import com.play.demo.entity.Inventory;
import com.play.demo.repository.InventoryRepository;

@Component
public class InventoryComponentImpl implements InventoryComponent {

	@Autowired
	InventoryRepository inventoryRepository;

	public List<Inventory> loadInventory() {

		return (List<Inventory>) inventoryRepository.findAll();
	}

}

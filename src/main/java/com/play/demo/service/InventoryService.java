package com.play.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.play.demo.component.InventoryComponent;
import com.play.demo.entity.Inventory;

@Service
public class InventoryService {

	@Autowired
	private InventoryComponent invComp;

	public List<Inventory> loadInventory() {
		List<Inventory> inv = invComp.loadInventory();
		return inv;
	}
}

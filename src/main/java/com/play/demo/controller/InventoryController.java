package com.play.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.play.demo.entity.Inventory;
import com.play.demo.service.InventoryService;

@RestController
public class InventoryController {

	@Autowired
	private InventoryService invService;

	@GetMapping("loadInventory")
	public List<Inventory> loadInventory() {

		List<Inventory> inv = invService.loadInventory();

		return inv;
	}
}

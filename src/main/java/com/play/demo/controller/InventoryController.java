package com.play.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.play.demo.dto.UserInfoDTO;
import com.play.demo.entity.Inventory;
import com.play.demo.service.InventoryService;

@RestController
public class InventoryController {

	private final InventoryService invService;

	@Autowired
	public InventoryController(InventoryService invService) {
		this.invService = invService;
	}

	@GetMapping("loadInventory")
	public List<Inventory> loadInventory() {

		List<Inventory> inv = invService.loadInventory();

		String url = "http://localhost:8081/userinfo/getuser";

		RestTemplate restTemplate = new RestTemplate();

		String requestJson = "1";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);

		UserInfoDTO userInfoDto = restTemplate.postForObject(url, entity, UserInfoDTO.class);

		System.out.println(userInfoDto);

		return inv;
	}
}

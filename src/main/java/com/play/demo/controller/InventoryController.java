package com.play.demo.controller;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.play.demo.dto.UserInfoDTO;
import com.play.demo.entity.Inventory;
import com.play.demo.service.InventoryService;

@RestController
public class InventoryController {

	@Autowired
	@Lazy
	private EurekaClient eurekaClient;

	private final InventoryService invService;

	@Autowired
	public InventoryController(InventoryService invService) {
		this.invService = invService;
	}

	@GetMapping("loadInventory")
	public List<Inventory> loadInventory() {

		List<Inventory> inv = invService.loadInventory();

		threadDemo();

//		callExtApiUsingRestTpl();

//		callExtApiUsingEureka();

		return inv;
	}

	// Main driver method
	public void threadDemo() {
		{
			// Creating objects of CountDownLatch class
			CountDownLatch cd1 = new CountDownLatch(5);
			CountDownLatch cd2 = new CountDownLatch(5);
			CountDownLatch cd3 = new CountDownLatch(5);
			CountDownLatch cd4 = new CountDownLatch(5);
			CountDownLatch cd5 = new CountDownLatch(5);

			// Creating objects of ExecutorService class
			ExecutorService es = Executors.newFixedThreadPool(2);

			// Display message only for better readability
			System.out.println("Starting");

			// Executing the tasks
			es.execute(new MyThread(cd1, "A"));
			es.execute(new MyThread(cd2, "B"));
			es.execute(new MyThread(cd3, "C"));
			es.execute(new MyThread(cd4, "D"));
			Future<String> callFuture = es.submit(new MyThreadCallable(cd5, "E"));
			try {
				String result = callFuture.get();
				System.out.println(result);
			} catch (InterruptedException | ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Try block to check for exceptions
			try {

				// Waiting for tasks to complete
				cd1.await();
				cd2.await();
				cd3.await();
				cd4.await();
				cd5.await();
			} catch (InterruptedException e) {

				System.out.println(e);
			}

			// Making all current executing threads to terminate
			es.shutdown();

			// Display message only for better readability
			System.out.println("Done");
		}
	}

	// Class 2
	// Helper class
	class MyThread implements Runnable {

		// Class data members
		String name;
		CountDownLatch latch;

		// Constructor
		MyThread(CountDownLatch latch, String name) {

			// this keyword refers to current instance itself
			this.name = name;
			this.latch = latch;

			new Thread(this);
		}

		// Method
		// Called automatically when thread is started
		public void run() {

			for (int i = 0; i < 5; i++) {
				System.out.println(name + ": " + i);
				latch.countDown();
			}
		}
	}

	class MyThreadCallable implements Callable<String> {

		String name;
		CountDownLatch latch;

		MyThreadCallable(CountDownLatch latch, String name) {

			this.name = name;
			this.latch = latch;

		}

		@Override
		public String call() throws Exception {
			for (int i = 0; i < 5; i++) {
				System.out.println(name + ": " + i);
				latch.countDown();
			}
			return "hi from callable";
		}
	}

	private void callExtApiUsingEureka() {

		InstanceInfo service = eurekaClient.getApplication("userinfo").getInstances().get(0);

		String hostName = service.getHostName();
		int port = service.getPort();

		System.out.println(hostName + port);
	}

	private void callExtApiUsingRestTpl() {

		String url = "http://localhost:8081/userinfo/getuser";

		RestTemplate restTemplate = new RestTemplate();

		String requestJson = "1";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);

		UserInfoDTO userInfoDto = restTemplate.postForObject(url, entity, UserInfoDTO.class);

		System.out.println(userInfoDto);
	}
}

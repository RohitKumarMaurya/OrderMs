package com.order.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.model.ItemModel;
import com.order.model.OrderModel;
import com.order.model.OrderTrackerModel;
import com.order.service.OrderLoadTestService;
import com.order.service.OrderService;

@RestController
@RequestMapping("/loadTest")
public class LoadTestController {
	@Autowired
	private OrderLoadTestService orderLoadTestService;

	@Autowired
	private OrderService orderService;

	@PostMapping
	public ResponseEntity<?> simulateLoad() throws InterruptedException {
		int numberOfOrders = 1000;

		// Executor to track all asynchronous calls
		List<CompletableFuture<OrderTrackerModel>> futures = new ArrayList<>();

		for (int i = 0; i < numberOfOrders; i++) {
			OrderModel order = new OrderModel();
			order.setUserId("rohit");
			order.setTotalAmount(100);

			List<ItemModel> items = new ArrayList<>();
			items.add(new ItemModel("1", "Pen", 10, 10));
			items.add(new ItemModel("2", "Book", 34, 5));
			items.add(new ItemModel("3", "Box", 75, 1));
			order.setItems(items);
			futures.add(orderLoadTestService.createOrderAsync(order, orderService));
		}

		// Wait for all tasks to complete
		CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

		return new ResponseEntity<>("Load test completed for " + numberOfOrders, HttpStatus.OK);
	}
}

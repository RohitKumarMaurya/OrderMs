package com.order.service.impl;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.order.exception.OrderException;
import com.order.model.OrderModel;
import com.order.model.OrderTrackerModel;
import com.order.service.OrderLoadTestService;
import com.order.service.OrderService;

@Service
public class OrderLoadTestServiceImpl implements OrderLoadTestService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderLoadTestServiceImpl.class);
	
    @Async
    public CompletableFuture<OrderTrackerModel> createOrderAsync(OrderModel order, OrderService orderService) {
        try {
            OrderTrackerModel trackerModel = orderService.createOrder(order);
            return CompletableFuture.completedFuture(trackerModel);
        } catch (OrderException e) {
        	LOGGER.error("Error creating order: {}", e);
            return CompletableFuture.completedFuture(null);
        }
    }
}

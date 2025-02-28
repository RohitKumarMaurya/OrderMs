package com.order.service;

import java.util.concurrent.CompletableFuture;

import com.order.model.OrderModel;
import com.order.model.OrderTrackerModel;

public interface OrderLoadTestService {

	CompletableFuture<OrderTrackerModel> createOrderAsync(OrderModel order, OrderService orderService);

}

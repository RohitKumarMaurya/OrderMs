package com.order.service;

import com.order.exception.OrderException;
import com.order.model.OrderModel;
import com.order.model.OrderTrackerModel;

public interface OrderService {

	OrderTrackerModel createOrder(OrderModel order) throws OrderException;

	OrderTrackerModel getOrderStatus(String orderId);

	OrderModel getOrderDetails(String orderId);

}

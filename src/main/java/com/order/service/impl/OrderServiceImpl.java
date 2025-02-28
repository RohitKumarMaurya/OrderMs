package com.order.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.order.dao.OrderDAO;
import com.order.dao.OrderTrackerDAO;
import com.order.dao.UserDAO;
import com.order.entity.OrderTracker;
import com.order.entity.Orders;
import com.order.entity.UserMaster;
import com.order.exception.OrderException;
import com.order.model.OrderModel;
import com.order.model.OrderTrackerModel;
import com.order.queue.OrderProducer;
import com.order.service.OrderService;
import com.order.util.Constants;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderProducer orderProducer;

	private static Gson gson = new Gson();

	@Autowired
	private OrderTrackerDAO orderTrackerDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private OrderDAO orderDAO;

	@Override
	public OrderTrackerModel createOrder(OrderModel order) throws OrderException {
		if(null == order.getUserId()) {
        	throw new OrderException(Constants.INVALID_USER);
        }
        Optional<UserMaster> user = userDAO.findById(order.getUserId());
        if(user.isEmpty()) {
        	throw new OrderException(Constants.INVALID_USER);
        }
		OrderTracker orderTracker = new OrderTracker();
		orderTracker.setOrderId(UUID.randomUUID().toString());
		orderTracker.setUserId(order.getUserId());
		orderTracker.setOrderStatus("REC");
		orderTracker.setInsertTimeStamp(LocalDateTime.now());
		orderTracker.setUpdateTimeStamp(LocalDateTime.now());
		orderTracker = orderTrackerDAO.save(orderTracker);
		order.setOrderId(orderTracker.getOrderId());
		String pushRes = orderProducer.pushOrder(gson.toJson(order));
		if (Constants.PUSH_SUCCESS.equals(pushRes)) {
			OrderTrackerModel orderTrackerModel = new OrderTrackerModel(orderTracker);
			return orderTrackerModel;
		}
		return null;
	}

	@Override
	public OrderTrackerModel getOrderStatus(String orderId) {
		Optional<OrderTracker> orderOptional = orderTrackerDAO.findById(orderId);
		if(orderOptional.isPresent()) {
			return new OrderTrackerModel(orderOptional.get());
		}
		return null;
	}

	@Override
	public OrderModel getOrderDetails(String orderId) {
		Optional<Orders> order = orderDAO.findById(orderId);
		if(order.isPresent()) {
			return new OrderModel(order.get());
		}
		return null;
	}

}

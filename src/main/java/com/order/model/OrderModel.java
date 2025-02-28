package com.order.model;

import java.util.List;

import com.google.gson.Gson;
import com.order.entity.Orders;

public class OrderModel {

	String orderId;

	String userId;

	List<ItemModel> items;

	double totalAmount;

	String orderTime;

	public OrderModel() {
		super();
	}
	
	public OrderModel(Orders order) {
		super();
		Gson gson = new Gson();
		this.orderId = order.getOrderId();
		this.userId = order.getUserId();
		this.items = (List<ItemModel>) gson.fromJson(order.getItems(), List.class);
		this.totalAmount = order.getTotalAmount();
		this.orderTime = order.getInsertTimeStamp().toString();
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<ItemModel> getItems() {
		return items;
	}

	public void setItems(List<ItemModel> items) {
		this.items = items;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	@Override
	public String toString() {
		return "OrderDAO [orderId=" + orderId + ", userId=" + userId + ", items=" + items + ", totalAmount=" + totalAmount
				+ ", orderTime=" + orderTime + "]";
	}
}

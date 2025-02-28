package com.order.model;

import java.util.List;

import com.google.gson.Gson;
import com.order.entity.Orders;

public class OrderModel {

	String orderId;

	String userId;

	List<ItemModel> items;

	double totalAmount;

	String insertTimeStamp;

	String updateTimeStamp;

	public OrderModel() {
		super();
	}
	
	public OrderModel(Orders order) {
		super();
		Gson gson = new Gson();
		this.orderId = order.getOrderId();
		this.userId = order.getUserId();
		this.items = (List<ItemModel>) gson.fromJson(order.getItems(), ItemModel.class);
		this.totalAmount = order.getTotalAmount();
		this.insertTimeStamp = order.getInsertTimeStamp().toString();
		this.updateTimeStamp = order.getUpdateTimeStamp().toString();
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

	public String getInsertTimeStamp() {
		return insertTimeStamp;
	}

	public void setInsertTimeStamp(String insertTimeStamp) {
		this.insertTimeStamp = insertTimeStamp;
	}

	public String getUpdateTimeStamp() {
		return updateTimeStamp;
	}

	public void setUpdateTimeStamp(String updateTimeStamp) {
		this.updateTimeStamp = updateTimeStamp;
	}

	@Override
	public String toString() {
		return "OrderDAO [orderId=" + orderId + ", userId=" + userId + ", items=" + items + ", totalAmount=" + totalAmount
				+ ", insertTimeStamp=" + insertTimeStamp + ", updateTimeStamp=" + updateTimeStamp + "]";
	}
}

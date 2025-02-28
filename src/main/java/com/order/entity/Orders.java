package com.order.entity;

import java.time.LocalDateTime;

import com.order.model.OrderModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders {

	@Id
	@Column(name = "orderid")
	String orderId;

	@Column(name = "userid")
	String userId;

	@Column(name = "items")
	String items;

	@Column(name = "totalamt")
	double totalAmount;

	@Column(name = "inserttmstmp")
	LocalDateTime insertTimeStamp;

	@Column(name = "updatetmstmp")
	LocalDateTime updateTimeStamp;

	public Orders() {
		super();
	}

	public Orders(OrderModel order, String items) {
		super();
		this.orderId = order.getOrderId();
		this.userId = order.getUserId();
		this.items = items;
		this.totalAmount = order.getTotalAmount();
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

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LocalDateTime getInsertTimeStamp() {
		return insertTimeStamp;
	}

	public void setInsertTimeStamp(LocalDateTime insertTimeStamp) {
		this.insertTimeStamp = insertTimeStamp;
	}

	public LocalDateTime getUpdateTimeStamp() {
		return updateTimeStamp;
	}

	public void setUpdateTimeStamp(LocalDateTime updateTimeStamp) {
		this.updateTimeStamp = updateTimeStamp;
	}

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", userId=" + userId + ", items=" + items + ", totalAmount=" + totalAmount
				+ ", insertTimeStamp=" + insertTimeStamp + ", updateTimeStamp=" + updateTimeStamp + "]";
	}
}

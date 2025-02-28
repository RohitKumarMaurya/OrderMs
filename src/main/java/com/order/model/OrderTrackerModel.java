package com.order.model;

import com.order.entity.OrderTracker;
import com.order.util.Constants;

public class OrderTrackerModel {

	String orderId;

	String userId;

	String orderStatus;

	String errorMessage;

	String insertTimeStamp;

	String updateTimeStamp;
	
	int ptime;

	public OrderTrackerModel() {
		super();
	}

	public OrderTrackerModel(OrderTracker orderTracker) {
		super();
		this.orderId = orderTracker.getOrderId();
		this.userId = orderTracker.getUserId();
		this.orderStatus = Constants.STATUS_MAP.get(orderTracker.getOrderStatus());
		this.errorMessage = Constants.ERR_MAP.get(orderTracker.getErrorCode());
		this.ptime = orderTracker.getPtime();
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

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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

	public int getPtime() {
		return ptime;
	}

	public void setPtime(int ptime) {
		this.ptime = ptime;
	}

	@Override
	public String toString() {
		return "OrderTrackerModel [orderId=" + orderId + ", userId=" + userId + ", orderStatus=" + orderStatus
				+ ", errorMessage=" + errorMessage + ", insertTimeStamp=" + insertTimeStamp + ", updateTimeStamp="
				+ updateTimeStamp + ", ptime=" + ptime + "]";
	}
}

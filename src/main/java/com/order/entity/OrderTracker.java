package com.order.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_tracker")
public class OrderTracker {

	@Id
	@Column(name = "orderid")
	String orderId;

	@Column(name = "userid")
	String userId;

	@Column(name = "orderstatus")
	String orderStatus;

	@Column(name = "errcd")
	String errorCode;

	@Column(name = "inserttmstmp")
	LocalDateTime insertTimeStamp;

	@Column(name = "updatetmstmp")
	LocalDateTime updateTimeStamp;
	
	@Column(name = "ptime", updatable = false, insertable = false)
	int ptime;

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

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
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

	public int getPtime() {
		return ptime;
	}

	public void setPtime(int ptime) {
		this.ptime = ptime;
	}

	@Override
	public String toString() {
		return "OrderTracker [orderId=" + orderId + ", userId=" + userId + ", orderStatus=" + orderStatus
				+ ", errorCode=" + errorCode + ", insertTimeStamp=" + insertTimeStamp + ", updateTimeStamp="
				+ updateTimeStamp + ", ptime=" + ptime + "]";
	}
}

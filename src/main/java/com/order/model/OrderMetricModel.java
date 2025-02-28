package com.order.model;

import java.util.Map;

public class OrderMetricModel {

	int totalOrders;
	
	double avgPTime;
	
	Map<String, Integer> orderStatus;

	public int getTotalOrders() {
		return totalOrders;
	}

	public void setTotalOrders(int totalOrders) {
		this.totalOrders = totalOrders;
	}

	public double getAvgPTime() {
		return avgPTime;
	}

	public void setAvgPTime(double avgPTime) {
		this.avgPTime = avgPTime;
	}

	public Map<String, Integer> getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Map<String, Integer> orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "OrderMetricModel [totalOrders=" + totalOrders + ", avgPTime=" + avgPTime + ", orderStatus="
				+ orderStatus + "]";
	}
}

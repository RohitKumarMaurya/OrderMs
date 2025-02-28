package com.order.service;

import com.order.model.OrderMetricModel;

public interface OrderMetricService {

	OrderMetricModel getOrderMetrics(String fromTime, String toTime);

	OrderMetricModel getOrderMetricsFromTime(String fromTime);

	OrderMetricModel getOrderMetricsToTime(String toTime);

	OrderMetricModel getAllOrderMetrics();

}

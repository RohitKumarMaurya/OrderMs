package com.order.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.dao.OrderTrackerDAO;
import com.order.model.OrderMetricModel;
import com.order.service.OrderMetricService;
import com.order.util.Constants;

@Service
public class OrderMetricServiceImpl implements OrderMetricService {

	@Autowired
	private OrderTrackerDAO orderTrackerDAO;

	private OrderMetricModel getOrderMetricModel(List<Object[]> result) {
		OrderMetricModel orderMetricModel = new OrderMetricModel();
		orderMetricModel.setOrderStatus(new HashMap<>());
		orderMetricModel.setTotalOrders(0);
		orderMetricModel.setAvgPTime(0);
		for (Object[] row : result) {
			orderMetricModel.getOrderStatus().put((String) row[0],
					orderMetricModel.getOrderStatus().getOrDefault((String) row[0], 0)
							+ ((Long) row[2]).intValue());

			orderMetricModel.setTotalOrders(orderMetricModel.getTotalOrders() + ((Long) row[2]).intValue());

			orderMetricModel.setAvgPTime(orderMetricModel.getAvgPTime() + (int) row[1]);
		}
		orderMetricModel.setAvgPTime(orderMetricModel.getAvgPTime() / result.size());
		return orderMetricModel;
	}

	@Override
	public OrderMetricModel getOrderMetrics(String fromTime, String toTime) {
		List<Object[]> result = orderTrackerDAO.getOrderStatusAndPtimeCount(
				LocalDateTime.parse(fromTime, DateTimeFormatter.ofPattern(Constants.DATE_PATTERN)),
				LocalDateTime.parse(toTime, DateTimeFormatter.ofPattern(Constants.DATE_PATTERN)));
		if (null != result && !result.isEmpty())
			return getOrderMetricModel(result);
		else
			return null;
	}

	@Override
	public OrderMetricModel getOrderMetricsFromTime(String fromTime) {
		List<Object[]> result = orderTrackerDAO.getOrderStatusAndPtimeCount(
				LocalDateTime.parse(fromTime, DateTimeFormatter.ofPattern(Constants.DATE_PATTERN)), LocalDateTime.now().withSecond(0).withNano(0));
		if (null != result && !result.isEmpty())
			return getOrderMetricModel(result);
		else
			return null;
	}

	@Override
	public OrderMetricModel getOrderMetricsToTime(String toTime) {
		List<Object[]> result = orderTrackerDAO
				.getOrderStatusAndPtimeCount(LocalDateTime.parse(toTime, DateTimeFormatter.ofPattern(Constants.DATE_PATTERN)));
		if (null != result && !result.isEmpty())
			return getOrderMetricModel(result);
		else
			return null;
	}

	@Override
	public OrderMetricModel getAllOrderMetrics() {
		List<Object[]> result = orderTrackerDAO.getOrderStatusAndPtimeCount();
		if (null != result && !result.isEmpty())
			return getOrderMetricModel(result);
		else
			return null;
	}

}

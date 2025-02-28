package com.order.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.order.dao.OrderTrackerDAO;
import com.order.model.OrderMetricModel;
import com.order.service.impl.OrderMetricServiceImpl;
import com.order.util.Constants;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OrderMetricServiceTest {
	
	@Mock
	private OrderTrackerDAO orderTrackerDAO;
	
	@InjectMocks
	private OrderMetricServiceImpl orderMetricService;


	@Test
	public void testGetOrderMetrics() {
	    // Mock data
	    String fromTime = "2025-01-01 00:00:00";
	    String toTime = "2025-02-01 00:00:00";
	    List<Object[]> mockResult = List.of(
	        new Object[]{"P", 1, 10L},
	        new Object[]{"REC", 0, 2L}
	    );
	    when(orderTrackerDAO.getOrderStatusAndPtimeCount(
	            LocalDateTime.parse(fromTime, DateTimeFormatter.ofPattern(Constants.DATE_PATTERN)),
	            LocalDateTime.parse(toTime, DateTimeFormatter.ofPattern(Constants.DATE_PATTERN))))
	           .thenReturn(mockResult);

	    // Test method
	    OrderMetricModel result = orderMetricService.getOrderMetrics(fromTime, toTime);

	    // Assert
	    assertNotNull(result);
	    assertEquals(12, result.getTotalOrders());
	    assertEquals(0.5, result.getAvgPTime());
	    assertEquals(2, result.getOrderStatus().size());
	    assertEquals(10, result.getOrderStatus().get("P").intValue());
	}
	
	@Test
	public void testGetOrderMetricsFromTime() {
	    // Mock data
	    String fromTime = "2025-01-01 00:00:00";
	    List<Object[]> mockResult = List.of(
	        new Object[]{"P", 1, 10L},
	        new Object[]{"REC", 0, 2L}
	    );
	    when(orderTrackerDAO.getOrderStatusAndPtimeCount(
	            LocalDateTime.parse(fromTime, DateTimeFormatter.ofPattern(Constants.DATE_PATTERN)),
	            LocalDateTime.now().withSecond(0).withNano(0)))
	           .thenReturn(mockResult);

	    // Test method
	    OrderMetricModel result = orderMetricService.getOrderMetricsFromTime(fromTime);

	    // Assert
	    assertNotNull(result);
	    assertEquals(12, result.getTotalOrders());
	    assertEquals(0.5, result.getAvgPTime());
	    assertEquals(2, result.getOrderStatus().size());
	    assertEquals(10, result.getOrderStatus().get("P").intValue());
	}
	
	@Test
	public void testGetOrderMetricsToTime() {
	    // Mock data
	    String toTime = "2025-02-01 00:00:00";
	    List<Object[]> mockResult = List.of(
	        new Object[]{"P", 1, 10L},
	        new Object[]{"REC", 0, 2L}
	    );
	    when(orderTrackerDAO.getOrderStatusAndPtimeCount(
	            LocalDateTime.parse(toTime, DateTimeFormatter.ofPattern(Constants.DATE_PATTERN))))
	           .thenReturn(mockResult);

	    // Test method
	    OrderMetricModel result = orderMetricService.getOrderMetricsToTime(toTime);

	    // Assert
	    assertNotNull(result);
	    assertEquals(12, result.getTotalOrders());
	    assertEquals(0.5, result.getAvgPTime());
	    assertEquals(2, result.getOrderStatus().size());
	    assertEquals(10, result.getOrderStatus().get("P").intValue());
	}
	
	@Test
	public void testGetAllOrderMetrics() {
	    // Mock data
	    List<Object[]> mockResult = List.of(
	        new Object[]{"P", 1, 10L},
	        new Object[]{"REC", 0, 2L}
	    );
	    when(orderTrackerDAO.getOrderStatusAndPtimeCount())
	           .thenReturn(mockResult);

	    // Test method
	    OrderMetricModel result = orderMetricService.getAllOrderMetrics();

	    // Assert
	    assertNotNull(result);
	    assertEquals(12, result.getTotalOrders());
	    assertEquals(0.5, result.getAvgPTime());
	    assertEquals(2, result.getOrderStatus().size());
	    assertEquals(10, result.getOrderStatus().get("P").intValue());
	}

}

package com.order.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.order.dao.OrderTrackerDAO;
import com.order.dao.UserDAO;
import com.order.entity.OrderTracker;
import com.order.entity.UserMaster;
import com.order.exception.OrderException;
import com.order.model.OrderModel;
import com.order.model.OrderTrackerModel;
import com.order.queue.OrderProducer;
import com.order.service.impl.OrderServiceImpl;
import com.order.util.Constants;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
	
	@Mock
	private OrderTrackerDAO orderTrackerDAO;
	
	@InjectMocks
	private OrderServiceImpl orderService;

    @Mock
    private OrderProducer orderProducer;
    
    @Mock
    private UserDAO	userDAO;


    @Test
    public void testCreateOrder_Success() throws OrderException {
    	// Mock data
        OrderModel order = new OrderModel();
        order.setUserId("testUserId");
        UserMaster user = new UserMaster();
        user.setUserId("testUserId");
        when(userDAO.findById("testUserId")).thenReturn(Optional.of(user));

        OrderTracker mockOrderTracker = new OrderTracker();
        mockOrderTracker.setOrderId("testOrderId");
        mockOrderTracker.setUserId(order.getUserId());
        mockOrderTracker.setOrderStatus("REC");
        mockOrderTracker.setInsertTimeStamp(LocalDateTime.now());
        mockOrderTracker.setUpdateTimeStamp(LocalDateTime.now());

        when(orderTrackerDAO.save(any(OrderTracker.class))).thenReturn(mockOrderTracker);
        when(orderProducer.pushOrder(anyString())).thenReturn(Constants.PUSH_SUCCESS);

        // Test method
        OrderTrackerModel result = orderService.createOrder(order);

        // Assertions
        assertNotNull(result);
        assertEquals("testOrderId", result.getOrderId());
        assertEquals("testUserId", result.getUserId());
        assertEquals("Pending", result.getOrderStatus());
        verify(orderTrackerDAO, times(1)).save(any(OrderTracker.class));
        verify(orderProducer, times(1)).pushOrder(anyString());
    }
    
    @Test
    public void testCreateOrder_InvalidUser() {
    	// Mock data
    	OrderModel order = new OrderModel();
        order.setUserId("testUserId");
        order.setUserId(null);

        // Test method
        OrderException exception = assertThrows(OrderException.class, () -> {
            orderService.createOrder(order);
        });
        
        // Assertions
        assertEquals(Constants.ERR_MAP.get(Constants.INVALID_USER), exception.getMessage());
    }

    @Test
    public void testCreateOrder_UserNotFound() {
    	// Mock data
    	OrderModel order = new OrderModel();
        order.setUserId("testUserId");
        when(userDAO.findById("testUserId")).thenReturn(Optional.empty());
        

        // Test method
        OrderException exception = assertThrows(OrderException.class, () -> {
            orderService.createOrder(order);
        });
        
        // Assertions
        assertEquals(Constants.ERR_MAP.get(Constants.INVALID_USER), exception.getMessage());
    }

    @Test
    public void testCreateOrder_PushFailed() throws OrderException {
    	// Mock data
        OrderModel order = new OrderModel();
        order.setUserId("testUserId");
        UserMaster user = new UserMaster();
        user.setUserId("testUserId");
        when(userDAO.findById("testUserId")).thenReturn(Optional.of(user));
        
        OrderTracker mockOrderTracker = new OrderTracker();
        mockOrderTracker.setOrderId("testOrderId");
        mockOrderTracker.setUserId(order.getUserId());
        mockOrderTracker.setOrderStatus("REC");
        mockOrderTracker.setInsertTimeStamp(LocalDateTime.now());
        mockOrderTracker.setUpdateTimeStamp(LocalDateTime.now());

        when(orderTrackerDAO.save(any(OrderTracker.class))).thenReturn(mockOrderTracker);
        when(orderProducer.pushOrder(anyString())).thenReturn("PUSH_FAILED");

        // Test method
        OrderTrackerModel result = orderService.createOrder(order);

        // Assertions
        assertNull(result);
        verify(orderTrackerDAO, times(1)).save(any(OrderTracker.class));
        verify(orderProducer, times(1)).pushOrder(anyString());
    }
    
    @Test
    public void testGetOrderStatus_OrderExists() {
        // Mock data
        String orderId = "testOrderId";
        OrderTracker mockOrderTracker = new OrderTracker();
        mockOrderTracker.setOrderId(orderId);
        mockOrderTracker.setUserId("testUserId");
        mockOrderTracker.setOrderStatus("P");
        when(orderTrackerDAO.findById(orderId)).thenReturn(Optional.of(mockOrderTracker));

        // Test method
        OrderTrackerModel result = orderService.getOrderStatus(orderId);

        // Assertions
        assertNotNull(result);
        assertEquals(orderId, result.getOrderId());
        assertEquals("testUserId", result.getUserId());
        assertEquals("Completed", result.getOrderStatus());
    }

    @Test
    public void testGetOrderStatus_OrderDoesNotExist() {
        // Mock data
        String orderId = "12345";
        when(orderTrackerDAO.findById(orderId)).thenReturn(Optional.empty());

        // Test method
        OrderTrackerModel result = orderService.getOrderStatus(orderId);

        // Assertions
        assertNull(result);
    }

}

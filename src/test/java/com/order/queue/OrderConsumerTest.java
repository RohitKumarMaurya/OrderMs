package com.order.queue;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.order.dao.ItemMasterDAO;
import com.order.dao.OrderDAO;
import com.order.dao.OrderTrackerDAO;
import com.order.entity.ItemMaster;
import com.order.entity.OrderTracker;
import com.order.model.ItemModel;
import com.order.model.OrderModel;

@SpringBootTest
@ExtendWith(MockitoExtension.class)

public class OrderConsumerTest {
	
	@Mock
	private OrderTrackerDAO orderTrackerDAO;
	
	@Mock
	private ItemMasterDAO itemMasterDAO;
	
	@InjectMocks
	private OrderConsumer orderConsumer;
	
	@Mock
	private OrderDAO orderDAO;
	
	@Test
	public void testProcessOrder_ValidData() {
	    // Mock data
	    String message = "{\"orderId\":\"12345\",\"items\":[{\"itemId\":\"item1\",\"itemQty\":2}]}";
	    OrderModel orderModel = new OrderModel();
	    orderModel.setOrderId("12345");
	    ItemModel itemModel = new ItemModel();
	    itemModel.setItemId("item1");
	    itemModel.setItemQty(2);
	    orderModel.setItems(List.of(itemModel));
	    OrderTracker orderTracker = new OrderTracker();
	    orderTracker.setOrderId("12345");
	    orderTracker.setOrderStatus("IP");
	    
	    ItemMaster itemMaster = new ItemMaster();
	    itemMaster.setItemId("item1");
	    itemMaster.setItemRate( 100.0);
	    itemMaster.setItemQty(10);
	    
	    when(orderTrackerDAO.findById("12345")).thenReturn(Optional.of(orderTracker));
	    when(itemMasterDAO.findAllById(Set.of("item1"))).thenReturn(List.of(itemMaster));
	    when(orderTrackerDAO.saveAndFlush(any())).thenReturn(orderTracker);

	    // Test method
	    orderConsumer.processOrder(message);

	    // Assert
	    verify(orderTrackerDAO, times(1)).save(any());
	    verify(orderDAO, times(1)).save(any());
	    verify(itemMasterDAO, times(1)).saveAll(anyList());
	}

	@Test
	public void testProcessOrder_OrderNotFound() {
	    // Mock data
	    String message = "{\"orderId\":\"12345\",\"items\":[{\"itemId\":\"item1\",\"itemQty\":2}]}";
	    when(orderTrackerDAO.findById("12345")).thenReturn(Optional.empty());

	    // Test method
	    orderConsumer.processOrder(message);

	    // Assert
	    verify(orderTrackerDAO, never()).save(any());
	    verify(orderDAO, never()).save(any());
	    verify(itemMasterDAO, never()).saveAll(anyList());
	}
	
	@Test
	public void testProcessOrder_ItemMismatch() {
	    // Mock data
	    String message = "{\"orderId\":\"12345\",\"items\":[{\"itemId\":\"item1\",\"itemQty\":2}]}";
	    OrderTracker orderTracker = new OrderTracker();
	    orderTracker.setOrderId("12345");
	    
	    when(orderTrackerDAO.findById("12345")).thenReturn(Optional.of(orderTracker));
	    when(itemMasterDAO.findAllById(Set.of("item1"))).thenReturn(List.of()); // No items found
	    when(orderTrackerDAO.saveAndFlush(any())).thenReturn(orderTracker);
	    
	    // Test method
	    orderConsumer.processOrder(message);

	    // Assert
	    verify(orderTrackerDAO, times(1)).save(any());
	    verify(orderDAO, never()).save(any());
	    verify(itemMasterDAO, never()).saveAll(anyList());
	}

	@Test
	public void testProcessOrder_ItemsMismatch() {
	    // Mock data
	    String message = "{\"orderId\":\"12345\",\"items\":[{\"itemId\":\"item2\",\"itemQty\":5}]}";
	    OrderModel orderModel = new OrderModel();
	    orderModel.setOrderId("12345");
	    ItemModel itemModel = new ItemModel();
	    itemModel.setItemId("item2");
	    itemModel.setItemQty(5);
	    orderModel.setItems(List.of(itemModel));
	    OrderTracker orderTracker = new OrderTracker();
	    orderTracker.setOrderId("12345");
	    ItemMaster itemMaster = new ItemMaster();
	    itemMaster.setItemId("item1");
	    itemMaster.setItemRate( 100.0);
	    itemMaster.setItemQty(10);
	    when(orderTrackerDAO.findById("12345")).thenReturn(Optional.of(orderTracker));
	    when(itemMasterDAO.findAllById(Set.of("item2"))).thenReturn(List.of(itemMaster));
	    when(orderTrackerDAO.saveAndFlush(any())).thenReturn(orderTracker);
	    
	    // Test method
	    orderConsumer.processOrder(message);

	    // Assert
	    verify(orderTrackerDAO, times(1)).save(any());
	    verify(orderDAO, never()).save(any());
	    verify(itemMasterDAO, never()).saveAll(anyList());
	}


}

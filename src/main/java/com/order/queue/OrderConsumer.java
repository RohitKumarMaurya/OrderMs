package com.order.queue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.order.dao.ItemMasterDAO;
import com.order.dao.OrderDAO;
import com.order.dao.OrderTrackerDAO;
import com.order.entity.ItemMaster;
import com.order.entity.OrderTracker;
import com.order.entity.Orders;
import com.order.model.ItemModel;
import com.order.model.OrderModel;
import com.order.util.Constants;

@Component
public class OrderConsumer {

	private static Gson gson = new Gson();

	@Autowired
	private OrderTrackerDAO orderTrackerDAO;

	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private ItemMasterDAO itemMasterDAO;

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);
	
	@JmsListener(destination = QueueConfig.ORDER_QUEUE)
	@Async
    public void createOrderAsync(String message) {
        try {
            CompletableFuture.completedFuture(processOrder(message));
        } catch (Exception e) {
        	LOGGER.error("Error creating order: {}", e);
        }
    }
	
	public int processOrder(String message) {
		OrderModel orderModel = gson.fromJson(message, OrderModel.class);
		Orders order = new Orders(orderModel, gson.toJson(orderModel.getItems()));
		Optional<OrderTracker> opt = orderTrackerDAO.findById(order.getOrderId());
		if (opt.isPresent()) {
			OrderTracker orderTracker = opt.get();
			orderTracker.setOrderStatus("IP");
			orderTracker.setUpdateTimeStamp(LocalDateTime.now());
			orderTracker = orderTrackerDAO.saveAndFlush(orderTracker);
			
			double total = 0;
			Map<String, ItemModel> itemMap = orderModel.getItems().stream()
					.collect(Collectors.toMap(ItemModel::getItemId, item -> item));
			List<ItemMaster> items = itemMasterDAO.findAllById(itemMap.keySet());
			if (items.size() == orderModel.getItems().size()) {
				for (ItemMaster item : items) {
					if (itemMap.containsKey(item.getItemId())) {
						total += item.getItemRate() * itemMap.get(item.getItemId()).getItemQty();
						item.setItemQty(item.getItemQty() - itemMap.get(item.getItemId()).getItemQty());
						item.setUpdateTimeStamp(LocalDateTime.now());
					} else {
						orderTracker.setOrderStatus("ER");
						orderTracker.setErrorCode(Constants.MISMATCH);
						orderTracker.setUpdateTimeStamp(LocalDateTime.now());
						orderTrackerDAO.save(orderTracker);
						return 0;
					}
				}
				orderTracker.setOrderStatus("P");
				order.setTotalAmount(total);
				order.setInsertTimeStamp(LocalDateTime.now());
				order.setUpdateTimeStamp(LocalDateTime.now());
				orderDAO.save(order);
				itemMasterDAO.saveAll(items);
			} else {
				orderTracker.setOrderStatus("ER");
				orderTracker.setErrorCode(Constants.MISMATCH);
			}
			orderTracker.setUpdateTimeStamp(LocalDateTime.now());
			orderTrackerDAO.save(orderTracker);
			return 1;
		}
		return 0;
	}
}

package com.order.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.order.exception.OrderException;
import com.order.model.ErrorModel;
import com.order.model.OrderModel;
import com.order.model.OrderTrackerModel;
import com.order.service.OrderService;
import com.order.util.Constants;
import com.order.util.ResponseUtility;

@RestController
@RequestMapping("/order")
public class OrderRest {
	
	@Autowired
	private  OrderService orderService;
	
	@Autowired
	private  ResponseUtility responseUtility;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderRest.class);

	@PostMapping
	public ResponseEntity<?> createOrder(@RequestBody OrderModel order) {
		LOGGER.info("Entered createOrder at {}", System.currentTimeMillis());
		try {
			if(null != order) {
				OrderTrackerModel orderTracker = orderService.createOrder(order);
				if(null != orderTracker) {
					String response = responseUtility.setSuccessResponse(orderTracker);
					return new ResponseEntity<>(response, HttpStatus.OK);
				} else {
					throw new OrderException(Constants.INVALID_ID);
				}
			} else {
				throw new OrderException(Constants.NO_DATA);
			}
		} catch(OrderException oe) {
			LOGGER.debug("OrderException in createOrder {}", oe);
			ErrorModel response = responseUtility.setFailureResponse(oe);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch(Exception e) {
			LOGGER.error("Exception in createOrder {}", e);
			ErrorModel response = responseUtility.setFailureResponse(new OrderException(Constants.SYS));
			return new ResponseEntity<>(response, HttpStatus.OK);
		} finally {
			LOGGER.info("Exiting createOrder at {}", System.currentTimeMillis());
		}
	}
	
	@GetMapping(value = "/status")
	public ResponseEntity<?> getOrderStatus(@RequestParam String orderId){
		LOGGER.info("Entered getOrderStatus at {}", System.currentTimeMillis());
		try {
			if(null != orderId) {
				OrderTrackerModel orderTracker = orderService.getOrderStatus(orderId);
				if(null != orderTracker) {
					String response = responseUtility.setSuccessResponse(orderTracker);
					return new ResponseEntity<>(response, HttpStatus.OK);
				} else {
					throw new OrderException(Constants.NOT_FOUND);
				}
			} else {
				throw new OrderException(Constants.INVALID_ID);
			}
		} catch(OrderException oe) {
			LOGGER.debug("OrderException in getOrderStatus {}", oe);
			ErrorModel response = responseUtility.setFailureResponse(oe);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch(Exception e) {
			LOGGER.error("Exception in getOrderStatus {}", e);
			ErrorModel response = responseUtility.setFailureResponse(new OrderException(Constants.SYS));
			return new ResponseEntity<>(response, HttpStatus.OK);
		} finally {
			LOGGER.info("Exiting getOrderStatus at {}", System.currentTimeMillis());
		}
	}
}

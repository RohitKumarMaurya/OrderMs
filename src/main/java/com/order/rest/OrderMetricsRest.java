package com.order.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.order.exception.OrderException;
import com.order.model.ErrorModel;
import com.order.model.OrderMetricModel;
import com.order.service.OrderMetricService;
import com.order.util.Constants;
import com.order.util.ResponseUtility;

@RestController
@RequestMapping("/orderMetrics")
public class OrderMetricsRest {

	@Autowired
	private OrderMetricService orderMetricService;

	@Autowired
	private ResponseUtility responseUtility;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderMetricsRest.class);

	@GetMapping
	public ResponseEntity<?> getOrderMetrics(@RequestParam(required = false) String fromTime, @RequestParam(required = false) String toTime) {
		LOGGER.info("Entered getOrderMetrics at {}", System.currentTimeMillis());
		try {
			OrderMetricModel orderMetricModel = null;
			if (null != fromTime && null != toTime) {
				orderMetricModel = orderMetricService.getOrderMetrics(fromTime, toTime);
			} else if (null == fromTime && null != toTime) {
				orderMetricModel = orderMetricService.getOrderMetricsFromTime(fromTime);
			} else if (null == toTime && null != fromTime) {
				orderMetricModel = orderMetricService.getOrderMetricsToTime(toTime);
			} else {
				orderMetricModel = orderMetricService.getAllOrderMetrics();
			}

			if (null != orderMetricModel) {
				String response = responseUtility.setSuccessResponse(orderMetricModel);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				throw new OrderException(Constants.NO_DATA_FOUND);
			}
		} catch (OrderException oe) {
			LOGGER.debug("OrderException in getOrderMetrics {}", oe);
			ErrorModel response = responseUtility.setFailureResponse(oe);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Exception in getOrderMetrics {}", e);
			ErrorModel response = responseUtility.setFailureResponse(new OrderException(Constants.SYS));
			return new ResponseEntity<>(response, HttpStatus.OK);
		} finally {
			LOGGER.info("Exiting getOrderMetrics at {}", System.currentTimeMillis());
		}
	}
}

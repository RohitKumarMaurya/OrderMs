package com.order.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.order.util.Constants;

@Component
public class OrderProducer {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);

	public String pushOrder(String message) {
		LOGGER.info("Entered pushOrder at {}", System.currentTimeMillis());
		try {
			jmsTemplate.convertAndSend(QueueConfig.ORDER_QUEUE, message);
			return Constants.PUSH_SUCCESS;
		} catch (Exception e) {
			LOGGER.error("Exception in pushOrder {}", e);
		} finally {
			LOGGER.info("Exiting pushOrder at {}", System.currentTimeMillis());
		}
		return Constants.PUSH_FAIL;
	}
}

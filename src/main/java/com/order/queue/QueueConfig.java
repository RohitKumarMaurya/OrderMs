package com.order.queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import jakarta.jms.Queue;

@EnableJms
@Configuration
public class QueueConfig {

	public static final String ORDER_QUEUE = "order.queue";

	private static final Logger LOGGER = LoggerFactory.getLogger(QueueConfig.class);

	@Bean
	public Queue queue() {
		LOGGER.info("Entered queue at {}", System.currentTimeMillis());
		try {
			return new ActiveMQQueue(ORDER_QUEUE);
		} catch (Exception e) {
			LOGGER.error("Exception in queue {}", e);
		} finally {
			LOGGER.info("Exiting queue at {}", System.currentTimeMillis());
		}
		return null;
	}

}

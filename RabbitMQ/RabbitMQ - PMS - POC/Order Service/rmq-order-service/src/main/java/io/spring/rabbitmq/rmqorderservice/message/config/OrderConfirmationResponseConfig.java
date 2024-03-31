package io.spring.rabbitmq.rmqorderservice.message.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfirmationResponseConfig {

	public static final String ORDER_CONFIRMATION_QUEUE = "order.confirmation.queue";
	public static final String ORDER_SERVICE_EXCHANGE = "order.service.exchange";
	public static final String ORDER_PLACED_ROUTING_KEY = "order.confirmed";

	@Bean
	public Queue orderConfirmationQueue() {
		return new Queue(ORDER_CONFIRMATION_QUEUE, true); // Durable queue
	}

	@Bean
	public DirectExchange inventoryServiceExchange() { 
		return new DirectExchange(ORDER_SERVICE_EXCHANGE);
	}

}

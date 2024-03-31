package io.spring.rabbitmq.rmqinventoryservice.message.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfirmationMessageConfig {

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

	@Bean
	public Binding orderConfirmationBinding(Queue orderConfirmationQueue, DirectExchange inventoryServiceExchange) { // Renamed
		
		return BindingBuilder.bind(orderConfirmationQueue)
				.to(inventoryServiceExchange)
				.with(ORDER_PLACED_ROUTING_KEY);
	}

}

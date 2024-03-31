package io.spring.rabbitmq.rmqinventoryservice.message.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderMessageConfig {

	public static final String ORDER_STATUS_QUEUE = "order.status.queue";
	public static final String INVENTORY_EVENTS_EXCHANGE = "inventory.events.exchange";

	@Bean
	public Queue queue() {
		return new Queue(ORDER_STATUS_QUEUE, true);
	}

	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(INVENTORY_EVENTS_EXCHANGE);
	}

}

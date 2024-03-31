package io.spring.rabbitmq.rmqinventoryservice.message.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class OrderErrorMessageConfig {

	public static final String ORDER_ERROR_QUEUE = "order.error.queue";
	public static final String ORDER_SERVICE_EXCHANGE = "order.service.exchange";
	public static final String ORDER_ERROR_ROUTING_KEY = "order.error";

	@Bean
	public Queue orderErrorQueue() {
		return new Queue(ORDER_ERROR_QUEUE, true); // Durable queue
	}

	@Bean													//same direct exchange
	public Binding orderErrorBinding(Queue orderErrorQueue, DirectExchange orderServiceExchange) { 																								// Exchange
		return BindingBuilder.bind(orderErrorQueue).to(orderServiceExchange).with(ORDER_ERROR_ROUTING_KEY);
	}
}

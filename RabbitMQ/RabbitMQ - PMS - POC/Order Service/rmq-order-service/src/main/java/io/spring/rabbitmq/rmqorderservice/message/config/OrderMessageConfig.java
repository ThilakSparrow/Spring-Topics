package io.spring.rabbitmq.rmqorderservice.message.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class OrderMessageConfig {

	public static final String ORDER_STATUS_QUEUE = "order.status.queue";
	public static final String INVENTORY_EVENTS_EXCHANGE = "inventory.events.exchange";
	public static final String ORDER_STATUS_ROUTING_KEY = "order.status";

	@Bean
	public Queue queue() {
		return new Queue(ORDER_STATUS_QUEUE, true);
	}

	@Bean
	public TopicExchange inventoryEventsExchange() {
		return new TopicExchange(INVENTORY_EVENTS_EXCHANGE);
	}

	@Bean
	public Binding binding(Queue queue, TopicExchange inventoryEventsExchange) {
		return BindingBuilder.bind(queue).to(inventoryEventsExchange).with(ORDER_STATUS_ROUTING_KEY);
	}

	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost("localhost");
		connectionFactory.setPort(5672);
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		return connectionFactory;
	}

	@Bean
	public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate();
		rabbitTemplate.setConnectionFactory(connectionFactory);

		RetryTemplate retryTemplate = new RetryTemplate();
		ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();

		backOffPolicy.setInitialInterval(500);
		backOffPolicy.setMultiplier(10);
		backOffPolicy.setMaxInterval(10000);

		retryTemplate.setBackOffPolicy(backOffPolicy);
		rabbitTemplate.setRetryTemplate(retryTemplate);
		rabbitTemplate.setMessageConverter(messageConverter());

		return rabbitTemplate;
	}

}

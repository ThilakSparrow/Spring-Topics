package io.spring.rabbitmq.headersexchange;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
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
public class MessageConfig {

	public final static String ELECTRONICS_QUEUE = "Electronics";
	public final static String FOODS_QUEUE = "Food";
	public final static String GARMENTS_QUEUE = "Garments";

	public final static String PRODUCTS_EXCHANGE = "products.exchange.he";

	@Bean
	Queue electronicsQueue() {
		return new Queue(ELECTRONICS_QUEUE);
	}

	@Bean
	Queue foodsQueue() {
		return new Queue(FOODS_QUEUE);
	}

	@Bean
	Queue garmentsQueue() {
		return new Queue(GARMENTS_QUEUE);
	}

	@Bean
	HeadersExchange topicExchange() {
		return new HeadersExchange(PRODUCTS_EXCHANGE);
	}

	@Bean
	public Declarables declarable(Queue electronicsQueue, Queue garmentsQueue, Queue foodsQueue,
			HeadersExchange headersExchange) {

		Map<String, Object> electronicsHeaders = new HashMap<>();
		electronicsHeaders.put("category", "electronics");
		electronicsHeaders.put("urgency", "high");

		Map<String, Object> garmentsHeaders = new HashMap<>();
		garmentsHeaders.put("category", "garments");
		garmentsHeaders.put("urgency", "moderate");

		Map<String, Object> foodsHeaders = new HashMap<>();
		foodsHeaders.put("category", "food");
		foodsHeaders.put("urgency", "high");

		return new Declarables(
				BindingBuilder.bind(electronicsQueue).to(headersExchange).whereAll(electronicsHeaders).match(),
				BindingBuilder.bind(garmentsQueue).to(headersExchange).whereAny(garmentsHeaders).match(),
				BindingBuilder.bind(foodsQueue).to(headersExchange).whereAll(foodsHeaders).match());
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

package io.spring.rabbitmq.topicexchange.config;

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
public class MessageConfig {

	public final static String ELECTRONICS_QUEUE = "electronics";
	public final static String FOODS_QUEUE = "food";
	public final static String GARMENTS_QUEUE = "garments";
	
	public final static String TE_PRODUCTS_EXCHANGE = "te.products.exchange";
	
	public final static String ELECTRONICS_ROUTING_KEY = "*.electronics.*"; 
	public final static String GARMENTS_ROUTING_KEY = "*.garments.*"; 
	public final static String FOODS_ROUTING_KEY = "#.food"; 
	
	
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
	TopicExchange topicExchange() {
		return new TopicExchange(TE_PRODUCTS_EXCHANGE);
	}
	
	@Bean
	Binding binding(Queue electronicsQueue, TopicExchange exchange) {
		return BindingBuilder.bind(electronicsQueue).to(exchange).with(ELECTRONICS_ROUTING_KEY);
	}
	
	@Bean
	Binding binding1(Queue garmentsQueue, TopicExchange exchange) {
		return BindingBuilder.bind(garmentsQueue).to(exchange).with(GARMENTS_ROUTING_KEY);
	}
	
	@Bean
	Binding binding2(Queue foodsQueue, TopicExchange exchange) {
		return BindingBuilder.bind(foodsQueue).to(exchange).with(FOODS_ROUTING_KEY);
	}
	
	@Bean
	MessageConverter messageConvertor() {
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
		rabbitTemplate.setMessageConverter(messageConvertor());
		
		return rabbitTemplate;
	}
}
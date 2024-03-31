package io.spring.rabbitmq.paymentservice.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
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
public class PaymentMessageConfig {

	public static final String PAYMENT_QUEUE = "payment.queue";
	public static final String PAYMENT_EVENTS_EXCHANGE = "payment.events.exchange";
	public static final String PAYMENT_ROUTING_KEY = "payment.routing.key";

	@Bean
	public Queue paymentQueue() {
		return new Queue(PAYMENT_QUEUE, true);
	}

	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange(PAYMENT_EVENTS_EXCHANGE);
	}
	
	@Bean
	public Binding paymentBinding(Queue paymentQueue, DirectExchange directExchange) {
		return BindingBuilder.bind(paymentQueue).to(directExchange).with(PAYMENT_ROUTING_KEY);
	}
	
	@Bean 
	MessageConverter messageConverter() {
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


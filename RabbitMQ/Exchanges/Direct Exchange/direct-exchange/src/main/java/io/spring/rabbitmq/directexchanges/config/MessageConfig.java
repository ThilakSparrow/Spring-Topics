package io.spring.rabbitmq.directexchanges.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
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

//	public final static String ELECTRONICS_QUEUE = "electronics"; 
//	public final static String GARMENTS_QUEUE = "garments";
//	public final static String FOODS_QUEUE = "foods";
//	
//	public final static String DE_PRODUCT_EXCHANGE = "de.products.exchange";
//	
//	
//	public final static String ELECTRONICS_ROUTING_KEY = "electronics"; 
//	public final static String GARMENTS_ROUTING_KEY = "garments"; 
//	public final static String FOODS_ROUTING_KEY = "foods.queue"; 
	
	@Bean
	public Queue electronicsQueue() {
		return new Queue("Electronics");
	}
	
	
	@Bean 
	Exchange directExchange() {
		return new DirectExchange("directExchange");
	}
	
	 @Bean
	    public Binding directBindingElectronics(
	            Queue electronicsQueue,
	            DirectExchange directExchange) {
	        return BindingBuilder.bind(electronicsQueue).to(directExchange).with("electronics");
	    }
//
//	    @Bean
//	    public Binding directBindingGarments(
//	            @Qualifier("garmentsQueue") Queue garmentsQueue,
//	            DirectExchange directExchange) {
//	        return BindingBuilder.bind(garmentsQueue).to(directExchange).with(GARMENTS_ROUTING_KEY);
//	    }
//
//	    @Bean
//	    public Binding directBindingFoods(
//	            @Qualifier("foodsQueue") Queue foodsQueue,
//	            DirectExchange directExchange) {
//	        return BindingBuilder.bind(foodsQueue).to(directExchange).with(FOODS_ROUTING_KEY);
//	    }
//	
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

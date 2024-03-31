package io.spring.rabbitmq.paymentservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductDetailsRequestMessageConfig {

	public final static String PRODUCT_DETAILS_QUEUE = "products.details.queue";
	public final static String PRODUCTS_EXCHANGE = "products.exchange";
	public final static String PRODUCT_DETAILS_ROUTING_KEY = "product.details";

	@Bean
	public Queue queue() {
		return new Queue(PRODUCT_DETAILS_QUEUE, true);
	}

	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(PRODUCTS_EXCHANGE);
	}

	@Bean
	public Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(PRODUCT_DETAILS_ROUTING_KEY);
	}
	
}

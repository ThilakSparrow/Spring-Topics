package io.spring.rabbitmq.rmqinventoryservice.message.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductsMessageConfig {

	public final static String PRODUCT_UPDATES_QUEUE = "product.updates.queue";
	public final static String PRODUCT_EVENTS_EXCHANGE = "inventory.events.exchange";
	public final static String PRODUCT_UPDATES_ROUTING_KEY = "product.updates";

	@Bean
	public Queue productUpdatesQueue() {
		return new Queue(PRODUCT_UPDATES_QUEUE, true);
	}

	@Bean
	public TopicExchange productEventsExchange() {
		return new TopicExchange(PRODUCT_EVENTS_EXCHANGE);
	}

	
//	@Bean
//	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
//			MessageListenerAdapter listenerAdapter) {
//		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//		container.setConnectionFactory(connectionFactory);
//		container.setQueueNames(INVENTORY_QUEUE);
//		container.setMessageListener(listenerAdapter);
//		return container;
//	}
//	
//	 @Bean
//	  MessageListenerAdapter listenerAdapter(InventoryService inventoryService) {
//	    return new MessageListenerAdapter(inventoryService, "handleReceivingMessage");
//	  }

}



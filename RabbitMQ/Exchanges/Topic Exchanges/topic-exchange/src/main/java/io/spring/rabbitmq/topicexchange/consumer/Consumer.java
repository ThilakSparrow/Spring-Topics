package io.spring.rabbitmq.topicexchange.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import io.spring.rabbitmq.topicexchange.config.Message;
import io.spring.rabbitmq.topicexchange.config.MessageConfig;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Consumer {

	@RabbitListener(queues = MessageConfig.FOODS_QUEUE)
	public void consumeFoodsQueueMessages(Message message) {
		
		log.debug("Listning to Food Queue ..., Message consumed --> {}",message);
		
	}
	
	@RabbitListener(queues = MessageConfig.GARMENTS_QUEUE)
	public void consumeGarmentsQueueMessages(Message message) {
		
		log.debug("Listning to Garment Queue ... , Message consumed --> {}",message);
		
	}
	
	@RabbitListener(queues = MessageConfig.ELECTRONICS_QUEUE)
	public void consumeElectronicsQueueMessages(Message message) {
		
		log.debug("Listning to Electronics Queue ...,Message consumed --> {}",message);
		
	}
	
}
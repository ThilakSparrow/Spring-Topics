package io.spring.rabbitmq.directexchange.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import io.spring.rabbitmq.directexchanges.config.Message;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Consumer {

//	@RabbitListener(queues = MessageConfig.FOODS_QUEUE)
//	public void consumeFoodsQueueMessages(Message message) {
//		log.debug("Listning to Food Queue ..., Message consumed --> {}",message);
//		System.out.println("Listning to Food Queue ..., Message consumed --> " + message);
//	}
//	
//	@RabbitListener(queues = MessageConfig.GARMENTS_QUEUE)
//	public void consumeGarmentsQueueMessages(Message message) {
//		
//		log.debug("Listning to Garment Queue ... , Message consumed --> {}",message);
//		System.out.println("Listning to Garment Queue ..., Message consumed --> " + message);
//	}
	
	@RabbitListener(queues = "electronics")
	public void consumeElectronicsQueueMessages(Message message) {
		
		log.debug("Listning to Electronics Queue ...,Message consumed --> {}",message);
		System.out.println("Listning to Electronics Queue ..., Message consumed --> " + message);
	}
	
}
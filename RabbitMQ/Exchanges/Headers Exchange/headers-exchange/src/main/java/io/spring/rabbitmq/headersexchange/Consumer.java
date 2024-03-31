package io.spring.rabbitmq.headersexchange;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	@RabbitListener(queues = MessageConfig.FOODS_QUEUE)
	public void consumeFoodsQueueMessages(Message message) {
		System.out.println("Listning to Food Queue ..., Message consumed --> " + message);
	}
	
	@RabbitListener(queues = MessageConfig.GARMENTS_QUEUE)
	public void consumeGarmentsQueueMessages(Message message) {
		System.out.println("Listning to Garment Queue ... , Message consumed --> " + message);
	}
	
	@RabbitListener(queues = MessageConfig.ELECTRONICS_QUEUE)
	public void consumeElectronicsQueueMessages(Message message) {
		System.out.println("Listning to Electronics Queue ...,Message consumed --> " + message);
	}
	
}

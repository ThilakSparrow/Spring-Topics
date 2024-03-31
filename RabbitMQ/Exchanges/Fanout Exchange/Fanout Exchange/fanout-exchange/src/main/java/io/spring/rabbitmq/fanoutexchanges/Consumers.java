package io.spring.rabbitmq.fanoutexchanges;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumers {

	@RabbitListener(queues = "Garments")
	public void groceriesQueueListener(Message message) {
		System.out.println("Listening to Groceries Queue, Message Received --> "  + message);
	}
	
	@RabbitListener(queues = "Electronics")
	public void gadgetsQueueListener(Message message) {	
		System.out.println("listening to Electronics Queue, Message Received --> "  + message);
	}
	
}
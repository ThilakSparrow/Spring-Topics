package io.spring.rabbitmq.headersexchange;

import java.util.Date;
import java.util.UUID;

import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Controller {

	@Autowired
	MessageConverter msgConverter;
	
	@Autowired
	RabbitTemplate template; 
	
	@PostMapping("/publish")
	public ResponseEntity<?> publishMessage(@RequestBody Message message){
		
		message.setMessageId(UUID.randomUUID().toString());
		message.setMessage(message.getMessage());
		message.setDate(new Date());
		
		MessageProperties properties = new MessageProperties();
		properties.setHeader("category", "garments");
		properties.setHeader("urgency", "high");
		
		org.springframework.amqp.core.Message message1 = msgConverter.toMessage(message, properties);
		
		template.convertAndSend(MessageConfig.PRODUCTS_EXCHANGE,"", message1);
		return ResponseEntity.ok("Message Published Successfully!");
	}
}
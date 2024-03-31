package io.spring.rabbitmq.fanoutexchanges;

import java.util.Date;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Controller {

	@Autowired
	RabbitTemplate template;
	
	@PostMapping("/publish")
	public ResponseEntity<?> publishMessage(@RequestBody Message message){
		
		message.setMessageId(UUID.randomUUID().toString());
		message.setMessage(message.getMessage());
		message.setDate(new Date());
		
		template.convertAndSend("fanoutExchange", "", message);
		
		return new ResponseEntity<>("Message Published !! " + message, HttpStatus.OK);
	}
	
}
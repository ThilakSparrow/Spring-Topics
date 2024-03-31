package io.spring.rabbitmq.directexchanges.config;

import java.util.Date;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Controller {

	@Autowired
	private RabbitTemplate template;
		
	@PostMapping("/publish")
	public String publish(@RequestBody Message message) {
		
		message.setMessageId(UUID.randomUUID().toString());
		message.setDate(new Date());
		
		template.convertAndSend("directExchange",
								"electronics",
								message);
		
		
		return "SUCCESS!!!";
	}
}
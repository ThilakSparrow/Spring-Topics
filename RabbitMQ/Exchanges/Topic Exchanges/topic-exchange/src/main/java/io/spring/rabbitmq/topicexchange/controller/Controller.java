package io.spring.rabbitmq.topicexchange.controller;

import java.util.Date;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.spring.rabbitmq.topicexchange.config.Message;
import io.spring.rabbitmq.topicexchange.config.MessageConfig;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping
@Slf4j
public class Controller {

	@Autowired
	private RabbitTemplate template;
		
	@PostMapping("/publish")
	public String publish(@RequestBody Message message) {
		
		message.setMessageId(UUID.randomUUID().toString());
		message.setDate(new Date());
		
		template.convertAndSend(MessageConfig.TE_PRODUCTS_EXCHANGE,
								MessageConfig.GARMENTS_ROUTING_KEY,
								message);
		
		log.debug("Message has been published --> {}",message);
		
		return "SUCCESS!!!";
	}
}

package io.spring.rabbitmq.topicexchange.config;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message{
	
	private String messageId;
	private String message;
	private Date date;
}

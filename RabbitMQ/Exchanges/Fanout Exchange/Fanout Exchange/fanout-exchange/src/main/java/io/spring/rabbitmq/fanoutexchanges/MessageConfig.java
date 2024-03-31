package io.spring.rabbitmq.fanoutexchanges;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class MessageConfig {

	@Bean
	public Queue electronicsQueue() {
		return new Queue("Electronics");
	}

	@Bean
	public Queue garmentsQueue() {
		return new Queue("Garments");
	}

	@Bean
	public FanoutExchange exchange() {
		return new FanoutExchange("fanoutExchange");
	}

	@Bean
	public Binding binding1(Queue electronicsQueue, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(electronicsQueue).to(fanoutExchange);
	}

	@Bean
	public Binding binding2(Queue garmentsQueue, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(garmentsQueue).to(fanoutExchange);
	}

	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost("localhost");
		connectionFactory.setPort(5672);
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		return connectionFactory;
	}

	@Bean
	public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate();
		rabbitTemplate.setConnectionFactory(connectionFactory);

		RetryTemplate retryTemplate = new RetryTemplate();
		ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();

		backOffPolicy.setInitialInterval(500);
		backOffPolicy.setMultiplier(10);
		backOffPolicy.setMaxInterval(10000);

		retryTemplate.setBackOffPolicy(backOffPolicy);
		rabbitTemplate.setRetryTemplate(retryTemplate);
		rabbitTemplate.setMessageConverter(messageConverter());

		return rabbitTemplate;
	}

}

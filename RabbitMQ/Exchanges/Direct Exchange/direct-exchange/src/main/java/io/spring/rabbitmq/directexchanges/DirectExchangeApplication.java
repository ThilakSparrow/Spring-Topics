package io.spring.rabbitmq.directexchanges;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class DirectExchangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DirectExchangeApplication.class, args);
	}

}

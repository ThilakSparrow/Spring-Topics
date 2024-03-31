package io.spring.rabbitmq.streamconsumer;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@SpringBootApplication
public class StreamConsumerApplication {

	public static final String STREAM = "myStream";

	public static void main(String[] args) throws IOException, TimeoutException {
		SpringApplication.run(StreamConsumerApplication.class, args);
	
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		
		Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        
//        Consumer consumer = new DefaultConsumer(channel) {
//            @Override
//            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                String message = new String(body);
//                System.out.println("Received message: " + message);
//                System.out.println("Message headers: " + properties.getHeaders()); // Access message headers if needed
//            }
//        };
//
//        int prefetchCount = 1;
//        channel.basicQos(prefetchCount, false);
//
//        channel.basicConsume(STREAM, true, consumer); 
//
//        System.out.println("Consumer started, waiting for messages...");
//    }
        
     // an hour ago
    Date timestamp = new Date(System.currentTimeMillis() - 60 * 60 * 1_000);
	
	channel.basicQos(100);
	channel.basicConsume(STREAM, false, Collections.singletonMap("x-stream-offset", timestamp),
			(consumerTag, message) -> {
				System.out.println("Received Message  -> " + message.getBody());
				System.out.println("Received Message  -> " + message.getEnvelope());
				System.out.println("Received Message  -> " + message.getProperties());
				
				channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
			},consumerTag -> { });
	}
}

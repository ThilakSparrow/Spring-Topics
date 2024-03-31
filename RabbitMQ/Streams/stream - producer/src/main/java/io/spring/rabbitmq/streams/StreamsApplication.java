package io.spring.rabbitmq.streams;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@SpringBootApplication
public class StreamsApplication {

	public static final String STREAM = "myStream";

	public static void main(String[] args) throws IOException, TimeoutException {
		SpringApplication.run(StreamsApplication.class, args);

		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");

		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();

		Map<String, Object> arguments = new HashMap<>();
		arguments.put("x-queue-type", "stream");
		arguments.put("x-max-length-bytes", 20_000_000_000L); // maximum stream size: 20 GB
		arguments.put("x-stream-max-segment-size-bytes", 100_000_000); // size of segment files: 100 MB

		channel.queueDeclare(STREAM, // stream name
				true, // is durable
				false, // is exclusive
				false, // is auto delete
				Collections.singletonMap("x-queue-type", "stream")); // this argument create stream

		String[] messages = { "Message 1", "Message 2", "Message 3" };
		
		AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
				.contentType("application/json") //set Content Type
				.headers(Collections.singletonMap("id", UUID.randomUUID().toString()))
				.build();
		
		for (String message : messages) {
			byte[] messageBytes = message.getBytes();
			channel.basicPublish("", STREAM, properties, messageBytes);
		}
		
		channel.close();
		connection.close();
	}
}
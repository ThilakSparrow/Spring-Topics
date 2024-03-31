package io.spring.rabbitmq.paymentservice.paymentService;

import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.spring.rabbitmq.paymentservice.config.PaymentMessageConfig;
import io.spring.rabbitmq.paymentservice.config.ProductDetailsRequestMessageConfig;
import io.spring.rabbitmq.paymentservice.message.OrderMessage;
import io.spring.rabbitmq.paymentservice.message.ProductDetailsMessage;

@Service
public class Listener {

	@Autowired
	RabbitTemplate template;

	@RabbitListener(queues = PaymentMessageConfig.PAYMENT_QUEUE)
	public void handlePayment(OrderMessage orderMessage) {

		System.out.println(orderMessage.getMessage());
		System.out.println(orderMessage);

		ProductDetailsMessage productDetailsMessage = new ProductDetailsMessage();

		productDetailsMessage.setMessageId(UUID.randomUUID().toString());
		productDetailsMessage.setMessage("I need the Product Details to Process the Payment ");
		productDetailsMessage.setSkuCode(orderMessage.getSkuCode());

		template.convertAndSend(ProductDetailsRequestMessageConfig.PRODUCTS_EXCHANGE,
				ProductDetailsRequestMessageConfig.PRODUCT_DETAILS_ROUTING_KEY, productDetailsMessage);
	}
	


	@RabbitListener(queues = ProductDetailsRequestMessageConfig.PRODUCT_DETAILS_QUEUE)
	public void handleProductDetails(ProductDetailsMessage productDetailsMessage) {
		
		
		
	}

	

}
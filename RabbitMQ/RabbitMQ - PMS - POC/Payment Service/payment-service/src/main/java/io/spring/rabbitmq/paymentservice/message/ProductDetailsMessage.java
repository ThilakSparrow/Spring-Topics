package io.spring.rabbitmq.paymentservice.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailsMessage {

	private String messageId;
	private String message;
	private String skuCode;
	private double price;
}

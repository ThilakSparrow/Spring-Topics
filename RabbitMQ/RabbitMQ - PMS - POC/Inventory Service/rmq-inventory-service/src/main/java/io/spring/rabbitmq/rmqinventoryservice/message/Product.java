package io.spring.rabbitmq.rmqinventoryservice.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	private Long id;
	private String productName;
	private String brand;
	private String description;
	private String skuCode;
	private double price;
	
}

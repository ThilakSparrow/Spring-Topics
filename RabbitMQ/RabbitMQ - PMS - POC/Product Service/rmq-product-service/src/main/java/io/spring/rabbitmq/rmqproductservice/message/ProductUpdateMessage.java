package io.spring.rabbitmq.rmqproductservice.message;

import java.util.Date;

import io.spring.rabbitmq.rmqproductservice.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductUpdateMessage {

	private String messageId;
	private String message;
//	private int quantity;
	private Product product;
	private Date date;
	private UpdateType updateType;
	
	public enum UpdateType {CREATE, UPDATE, DELETE}

	public ProductUpdateMessage(String messageId, String message, Product product, Date date, UpdateType updateType) {
		super();
		this.messageId = messageId;
		this.message = message;
		this.product = product;
		this.date = date;
		this.updateType = updateType;
	}

}

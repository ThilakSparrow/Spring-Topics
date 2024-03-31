package io.spring.rabbitmq.rmqinventoryservice.message;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderMessage {

	private String messageId;
	private String message;
	private String orderId;
	private String skuCode;
	private int quantity;
//	private boolean isFullfilable;
	private Date date;
//	private String responseMessage;
}

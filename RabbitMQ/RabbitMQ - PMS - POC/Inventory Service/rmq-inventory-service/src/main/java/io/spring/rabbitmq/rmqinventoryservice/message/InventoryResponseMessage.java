package io.spring.rabbitmq.rmqinventoryservice.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponseMessage {

	private String messageId;
	private String message;
	private String orderId;
	private boolean isFullfilable;

}

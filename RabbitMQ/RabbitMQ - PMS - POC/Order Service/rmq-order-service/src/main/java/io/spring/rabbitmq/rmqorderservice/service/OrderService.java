package io.spring.rabbitmq.rmqorderservice.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.spring.rabbitmq.rmqorderservice.dto.OrderDTO;
import io.spring.rabbitmq.rmqorderservice.message.InventoryResponseMessage;
import io.spring.rabbitmq.rmqorderservice.message.OrderMessage;
import io.spring.rabbitmq.rmqorderservice.message.config.OrderConfirmationResponseConfig;
import io.spring.rabbitmq.rmqorderservice.message.config.OrderErrorResponseConfig;
import io.spring.rabbitmq.rmqorderservice.message.config.OrderMessageConfig;
import io.spring.rabbitmq.rmqorderservice.model.Order;
import io.spring.rabbitmq.rmqorderservice.model.Order.OrderStatus;
import io.spring.rabbitmq.rmqorderservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	OrderRepository orderRepository;

	public ResponseEntity<?> placeOrder(OrderDTO orderDto) {

		Order order = new Order();

		String orderId = UUID.randomUUID().toString();
		log.debug("Generated OrderId : {}", orderId);

		order.setOrderId(orderId);
		order.setSkuCode(orderDto.getSkuCode());
		order.setQuantity(orderDto.getQuantity());
		order.setOrderStatus(null);

		orderRepository.save(order);
		log.info("Order Saved : {}", order);

		OrderMessage message = new OrderMessage();

		message.setMessageId(UUID.randomUUID().toString());
		message.setMessage("... New Order ...");
		message.setOrderId(orderId);
		message.setSkuCode(orderDto.getSkuCode());
		message.setQuantity(orderDto.getQuantity());
		message.setDate(new Date());

		template.convertAndSend(OrderMessageConfig.INVENTORY_EVENTS_EXCHANGE,
				OrderMessageConfig.ORDER_STATUS_ROUTING_KEY, message);

		log.info("Order Message Published : {}", message);

		return new ResponseEntity<>("Your order is processing ...", HttpStatus.OK);
	}

	@RabbitListener(queues = OrderConfirmationResponseConfig.ORDER_CONFIRMATION_QUEUE)
	public void handleOrderConfirmReponseStatus(InventoryResponseMessage inventoryResponse) {

		if (inventoryResponse.isFullfilable() == true)
			log.info("Inventory Service Response : {}", inventoryResponse);
		log.info("*** Order Placed Successfully ***");

		String orderID = inventoryResponse.getOrderId();
		Order order = orderRepository.findOrderByOrderId(orderID);
		log.debug("Order Data for Order ID : {} = {}", orderID, order);

		order.setOrderStatus(OrderStatus.PLACED);
		orderRepository.save(order);
		log.debug("Updated Order : {}", order);
	}

	@RabbitListener(queues = OrderErrorResponseConfig.ORDER_ERROR_QUEUE)
	public void handleOrderErrorReponseStatus(InventoryResponseMessage inventoryResponse) {
		if (inventoryResponse.isFullfilable() == false)
			log.info("*** Order Placement error ***");

		log.error("Invertory Response : {}", inventoryResponse);
		String orderID = inventoryResponse.getOrderId();

		Order order = orderRepository.findOrderByOrderId(orderID);
		log.debug("Order Data for Order ID : {} = {}", orderID, order);

		order.setOrderStatus(OrderStatus.PLACED);
		orderRepository.save(order);
		log.debug("Updated Order : {}", order);
	}

}

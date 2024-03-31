package io.spring.rabbitmq.rmqinventoryservice.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.spring.rabbitmq.rmqinventoryservice.message.InventoryResponseMessage;
import io.spring.rabbitmq.rmqinventoryservice.message.OrderMessage;
import io.spring.rabbitmq.rmqinventoryservice.message.config.OrderConfirmationMessageConfig;
import io.spring.rabbitmq.rmqinventoryservice.message.config.OrderErrorMessageConfig;
import io.spring.rabbitmq.rmqinventoryservice.message.config.OrderMessageConfig;
import io.spring.rabbitmq.rmqinventoryservice.model.InventoryItem;
import io.spring.rabbitmq.rmqinventoryservice.repository.InventoryRepository;

@Service
public class OrderServiceListener {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceListener.class);

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private RabbitTemplate template;

    @RabbitListener(queues = OrderMessageConfig.ORDER_STATUS_QUEUE)
    public void handleOrderMessage(OrderMessage message) {
        InventoryResponseMessage responseMessage = new InventoryResponseMessage();
        String requestedProductSkuCode = message.getSkuCode();

        // Find inventory item and check availability
        InventoryItem requestedProduct = inventoryRepository.findInventoryItemBySkuCode(requestedProductSkuCode);
        if (requestedProduct == null || message.getQuantity() <= 0 ||
                (requestedProduct != null && requestedProduct.getQuantity() < message.getQuantity())) {
            handleInsufficientInventory(message, responseMessage);
        } else {
            handleSufficientInventory(message, responseMessage);
        }
    }

    private void handleInsufficientInventory(OrderMessage message, InventoryResponseMessage responseMessage) {
        responseMessage.setMessageId(UUID.randomUUID().toString());
        responseMessage.setMessage("Order [Order ID: " + message.getOrderId() + "] cannot be fulfilled at this time. We apologize for any inconvenience this may cause.");
        responseMessage.setOrderId(message.getOrderId());
        responseMessage.setFullfilable(false);

        template.convertAndSend(OrderErrorMessageConfig.ORDER_ERROR_QUEUE, responseMessage);
        logger.warn("Order [Order ID: {}] cannot be fulfilled due to insufficient inventory.", message.getOrderId());
    }

    private void handleSufficientInventory(OrderMessage message, InventoryResponseMessage responseMessage) {
        responseMessage.setMessageId(UUID.randomUUID().toString());
        responseMessage.setMessage("Your order [Order ID: " + message.getOrderId() + "] is confirmed!");
        responseMessage.setOrderId(message.getOrderId());
        responseMessage.setFullfilable(true);

        template.convertAndSend(OrderConfirmationMessageConfig.ORDER_CONFIRMATION_QUEUE, responseMessage);
        logger.info("Order [Order ID: {}] confirmed!", message.getOrderId());
    }
}

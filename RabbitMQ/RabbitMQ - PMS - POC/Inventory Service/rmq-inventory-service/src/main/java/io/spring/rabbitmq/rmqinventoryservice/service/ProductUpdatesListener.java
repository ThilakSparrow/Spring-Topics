package io.spring.rabbitmq.rmqinventoryservice.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.spring.rabbitmq.rmqinventoryservice.message.ProductUpdateMessage;
import io.spring.rabbitmq.rmqinventoryservice.message.config.ProductsMessageConfig;
import io.spring.rabbitmq.rmqinventoryservice.model.InventoryItem;
import io.spring.rabbitmq.rmqinventoryservice.repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductUpdatesListener {

	@Autowired
	private InventoryRepository inventoryRepository;

	@RabbitListener(queues = ProductsMessageConfig.PRODUCT_UPDATES_QUEUE)
	public void handleProductMessage(ProductUpdateMessage message) {

		switch (message.getUpdateType()) {

		case CREATE:
			log.debug("Message Consumed -> {} , Product Update type : {}", message, message.getUpdateType());

			InventoryItem item = new InventoryItem();

			item.setProductName(message.getProduct().getProductName());
			item.setSkuCode(message.getProduct().getSkuCode());
			item.setQuantity(10);

			inventoryRepository.save(item);

			log.info("New Product Added , {}", item);

			break;

		case UPDATE:
			log.info("Product Updated");
			break;

		case DELETE:

			InventoryItem item2 = inventoryRepository.findInventoryItemBySkuCode(message.getProduct().getSkuCode());

			if (item2 == null) {
				throw new NullPointerException();
			}

			inventoryRepository.deleteById(item2.getId());
			log.info("Product Removed -> {}", item2);
			break;
		}
		log.info("Product update processed: {}", message);
	}

}

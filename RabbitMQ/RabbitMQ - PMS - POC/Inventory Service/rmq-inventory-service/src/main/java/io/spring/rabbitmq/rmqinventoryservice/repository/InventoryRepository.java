package io.spring.rabbitmq.rmqinventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.spring.rabbitmq.rmqinventoryservice.model.InventoryItem;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {

	InventoryItem findInventoryItemBySkuCode(String requestedProductSkuCode);

}

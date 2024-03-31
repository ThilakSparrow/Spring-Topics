package io.spring.rabbitmq.rmqorderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.spring.rabbitmq.rmqorderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	Order findOrderByOrderId(String orderId);
	
}

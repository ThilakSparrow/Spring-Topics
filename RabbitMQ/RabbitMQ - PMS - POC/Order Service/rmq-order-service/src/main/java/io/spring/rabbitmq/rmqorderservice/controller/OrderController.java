package io.spring.rabbitmq.rmqorderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.spring.rabbitmq.rmqorderservice.dto.OrderDTO;
import io.spring.rabbitmq.rmqorderservice.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	
	@PostMapping("/placeOrder")
	public ResponseEntity<?> placeOrder(@RequestBody OrderDTO order){
		return orderService.placeOrder(order);
	}
	
}

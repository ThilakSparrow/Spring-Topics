package io.spring.rabbitmq.rmqproductservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.spring.rabbitmq.rmqproductservice.model.Product;
import io.spring.rabbitmq.rmqproductservice.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService; 
	
	@PostMapping("/add")
	public ResponseEntity<?> addProdcut(@RequestBody Product product){
		return productService.addProduct(product);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") long id){
		return productService.deleteProduct(id);
	}
}

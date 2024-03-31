package io.spring.security.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.spring.security.exception.NoSuchProductFoundException;

@RestController
@RequestMapping("/api/v2/product")
public class ProductController {

		@Autowired ProductRepository productRepository;
		
		@PostMapping("/save")
		public ResponseEntity<?> saveProduct(@RequestBody Product product){
			productRepository.save(product);
			return ResponseEntity.ok("Product Saved Successfully");
		}
		
		
		@GetMapping("/get/{id}")
		public ResponseEntity<?> getProductbyId(@PathVariable int id){
			
			Product product = productRepository.findById(id)
					.orElseThrow(() -> new NoSuchProductFoundException("Product with "+id+" is not Found"));
			
			return ResponseEntity.ok(product);
		}
		
		

}

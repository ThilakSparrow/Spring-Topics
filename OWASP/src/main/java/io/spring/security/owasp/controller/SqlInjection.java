package io.spring.security.owasp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.spring.security.owasp.service.SqlInjectionService;

@RestController
@RequestMapping("/owasp/8")
public class SqlInjection {


	@Autowired SqlInjectionService sqlInjectionService;
	
	@GetMapping("/account-info-v/{id}")
	public ResponseEntity<?> accountInfo_v(@PathVariable(value = "id") String customerId)
	{
	   return sqlInjectionService.accountInfo_v(customerId);
	}
	
	
	@GetMapping("/account-info-s/{id}")
	public ResponseEntity<?> accountInfo_s(@PathVariable(value = "id") String customerId) {
		return sqlInjectionService.accountInfo_s(customerId);
	}
	
	

}

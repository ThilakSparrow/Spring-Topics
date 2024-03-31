package io.spring.security.owasp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.spring.security.owasp.service.SecurityMisconfigurationService;

@RestController
@RequestMapping("/owasp/7")
public class SecurityMisconfiguration {
	
	@Autowired SecurityMisconfigurationService securityMisconfigurationService;
	
	@GetMapping("/ping-v")
	public ResponseEntity<?> stackTrace_v() {
		return securityMisconfigurationService.stackTrace_v();
	}

	@GetMapping("/ping-s")
	public ResponseEntity<?> stackTrace_s(){
		return securityMisconfigurationService.stackTrace_s();
	}

}

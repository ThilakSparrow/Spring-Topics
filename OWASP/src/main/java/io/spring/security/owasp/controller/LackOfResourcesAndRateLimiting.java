package io.spring.security.owasp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.spring.security.owasp.service.LackOfResourcesAndRateLimitingService;

@RestController
@RequestMapping("/owasp/4")
public class LackOfResourcesAndRateLimiting {

	@Autowired
	LackOfResourcesAndRateLimitingService lackOfResourcesAndRateLimitingService;

	@PostMapping("/generate-otp-v")
	public ResponseEntity<?> generateOTP_v(@RequestParam String username) {
		return lackOfResourcesAndRateLimitingService.generateOTP_v(username);
	}

	@PostMapping("/generate-otp-s")
	public ResponseEntity<?> generateOTP_s(@RequestParam String username) {

		return lackOfResourcesAndRateLimitingService.generateOTP_s(username);

	}

}

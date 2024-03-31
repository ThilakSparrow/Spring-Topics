package io.spring.security.owasp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.spring.security.owasp.reqres.UserLoginRequest;
import io.spring.security.owasp.service.BrokenUserAuthenticationService;

@RestController
@RequestMapping("/owasp/2")
public class BrokenUserAuthentication {
	
	@Autowired BrokenUserAuthenticationService brokenUserAuthenticationService;

	@PostMapping("/login-v")
	public ResponseEntity<?> login(@RequestBody UserLoginRequest request) {
		return brokenUserAuthenticationService.login(request);
	}

	@PostMapping("/login-s")
	public ResponseEntity<?> login_s(@RequestBody UserLoginRequest request) {
		return brokenUserAuthenticationService.login_s(request);
	}

	
	
	
	
	
	
	
	
	
	
	

}

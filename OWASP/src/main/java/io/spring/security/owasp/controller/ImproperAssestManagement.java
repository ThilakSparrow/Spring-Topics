package io.spring.security.owasp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.spring.security.owasp.reqres.UserLoginRequest;
import io.spring.security.owasp.service.ImproperAssestManagementService;

@RestController
@RequestMapping("/owasp/9")
public class ImproperAssestManagement {

	@Autowired
	ImproperAssestManagementService improperAssestManagementService;

	@PostMapping("/v1/login")
	public ResponseEntity<?> userLogin_v(@RequestBody UserLoginRequest request) {
		return improperAssestManagementService.userLogin_v(request);
	}
	
	
	@PostMapping("/v2/login")
	public ResponseEntity<?> userLogin_s(@RequestBody UserLoginRequest request) {
		return improperAssestManagementService.userLogin_s(request);
	}

}

package io.spring.security.owasp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.spring.security.owasp.entity.Users;
import io.spring.security.owasp.service.MassAssignmentService;

@RestController
@RequestMapping("/owasp/5")
public class MassAssignment {
	
	@Autowired MassAssignmentService massAssignmentService;

	@PostMapping("/user-v")
	public ResponseEntity<?> signup_v(@RequestBody Users user){
		return massAssignmentService.signup_v(user);
	}
	
	@PostMapping("/user-s")
	public ResponseEntity<?> signup_s(@RequestBody Users user){
		return massAssignmentService.signup_s(user);
	}
	
	
	
	
}

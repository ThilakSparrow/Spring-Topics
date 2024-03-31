package io.spring.security.owasp.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.spring.security.owasp.repository.UserRepository;
import io.spring.security.owasp.service.BrokenObjectLevelControlService;

@RestController
@RequestMapping("/owasp/1")
public class BrokenObjectLevelControl {

	@Autowired
	UserRepository userRepository;
	
	@Autowired BrokenObjectLevelControlService brokenObjectLevelControlService; 


	@GetMapping("/user-profile-v")
	public ResponseEntity<?> showUserDetails_v(@RequestParam String username) {
		return brokenObjectLevelControlService.showUserDetails_v(username);
	}
	
	@GetMapping("/user-profile-s")
	public ResponseEntity<?> showUserDetails_s(@RequestParam String username, @RequestHeader("user-token") int token) {
		return brokenObjectLevelControlService.showUserDetails_s(username, token);
	}
	

}

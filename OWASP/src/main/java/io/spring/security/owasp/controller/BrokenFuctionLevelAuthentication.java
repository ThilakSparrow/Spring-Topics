package io.spring.security.owasp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.spring.security.owasp.service.BrokenFunctionLevelAuthenticationService;

@RestController
@RequestMapping("/owasp/6")
public class BrokenFuctionLevelAuthentication {

	@Autowired
	BrokenFunctionLevelAuthenticationService brokenFunctoinLevelAuthenticationService;

	@GetMapping("/profile-v/{id}")
	public ResponseEntity<?> showDetails(@PathVariable int id) {
		return brokenFunctoinLevelAuthenticationService.showDetails(id);
	}
	
	@GetMapping("/admin-profile/{id}")
	public ResponseEntity<?> showAdminDetails(@PathVariable int id) {
		return brokenFunctoinLevelAuthenticationService.showAdminDetails(id);
	}

	@GetMapping("/user-profile/{id}")
	public ResponseEntity<?> showUserDetails(@PathVariable int id) {
		return brokenFunctoinLevelAuthenticationService.showUserDetails(id);
	}

}

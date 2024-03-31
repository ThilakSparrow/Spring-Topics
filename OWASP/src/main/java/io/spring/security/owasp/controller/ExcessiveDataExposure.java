package io.spring.security.owasp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.spring.security.owasp.service.ExcessiveDataExposureService;

@RestController
@RequestMapping("/owasp/3")
public class ExcessiveDataExposure {

	@Autowired ExcessiveDataExposureService excessiveDataExposureService;
	
	@GetMapping("/user-details-v")
	public ResponseEntity<?> showUserDetails_v(@RequestParam String username) {
		return excessiveDataExposureService.showUserDetails_v(username);
	}
	
	@GetMapping("/user-details-s")
	public ResponseEntity<?> showUserDetails_s(@RequestParam String username) {
		return excessiveDataExposureService.showUserDetails_s(username);
	}

}
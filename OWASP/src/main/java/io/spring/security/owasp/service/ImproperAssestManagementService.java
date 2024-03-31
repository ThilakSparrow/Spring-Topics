package io.spring.security.owasp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import io.spring.security.owasp.entity.Users;
import io.spring.security.owasp.repository.UserRepository;
import io.spring.security.owasp.reqres.UserLoginRequest;

@Service
public class ImproperAssestManagementService {

	@Autowired
	UserRepository userRepository;

	public ResponseEntity<?> userLogin_v(UserLoginRequest request) {

		Users user = userRepository.findByUsername(request.getUsername());

		if (user != null) {
			if (request.getPassword().equals(user.getPassword())) {
				return ResponseEntity.ok("Login Successfull " + user);
			}
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");

		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");

	}

	public ResponseEntity<?> userLogin_s(UserLoginRequest request) {

		Users user = userRepository.findByUsername(request.getUsername());

		if (user != null) {
			if (request.getPassword().equals(user.getPassword())) {
				return ResponseEntity.ok("Login successful");
			}
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	}

}

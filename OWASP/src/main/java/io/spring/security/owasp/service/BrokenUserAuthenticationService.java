package io.spring.security.owasp.service;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.spring.security.owasp.entity.Users;
import io.spring.security.owasp.repository.UserRepository;
import io.spring.security.owasp.reqres.UserLoginRequest;

@Service
public class BrokenUserAuthenticationService {

	@Autowired
	UserRepository userRepository;

	public ResponseEntity<?> login(UserLoginRequest request) {
		Users user = userRepository.findByUsername(request.getUsername());
		if (user != null) {
			Random random = new Random();
			int token = random.nextInt(900000) + 100000;
			user.setToken(token);
			userRepository.save(user);
			return ResponseEntity.ok(Map.of("Token", token)); 
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	}
	

	public ResponseEntity<?> login_s(UserLoginRequest request) {
		Users user = userRepository.findByUsername(request.getUsername());

		if (user != null) {
			if (request.getPassword().equals(user.getPassword())) {
				Random random = new Random();
				int token = random.nextInt(900000) + 100000; 
				user.setToken(token);
				userRepository.save(user);
				return ResponseEntity.ok(Map.of("Token", token)); 
			}
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");

		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");

	}
}

package io.spring.security.owasp.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import io.spring.security.owasp.entity.Users;
import io.spring.security.owasp.repository.UserRepository;

@Service
public class LackOfResourcesAndRateLimitingService {

	@Autowired
	UserRepository userRepository;

	public ResponseEntity<?> generateOTP_v(@RequestParam String username) {

		Users user = userRepository.findByUsername(username);

		if (user == null)
			user = new Users();
		user.setUsername(username);
		
		int otp = new Random().nextInt(9000) + 1000;

		user.setTimestamp(LocalDateTime.now());
		user.setOtp(otp);

		userRepository.save(user);
		return ResponseEntity.ok(Map.of("OTP", otp));
	}

	
	public ResponseEntity<?> generateOTP_s(@RequestParam String username) {

		Users user = userRepository.findByUsername(username);

		
		if (user == null) {
			int otp = new Random().nextInt(9000)+1000;
			user = Users.builder().username(username).otp(otp).timestamp(LocalDateTime.now()).build();
			userRepository.save(user);
			return new ResponseEntity<>(Map.of("OTP", otp), HttpStatus.OK);
		}
		
		LocalDateTime timestamp = user.getTimestamp();
		Duration difference = Duration.between(timestamp, LocalDateTime.now());
        
        if (difference.compareTo(Duration.ofSeconds(10)) >= 0) {
        	int otp = new Random().nextInt(9000)+1000;
        	user.setOtp(otp);
        	user.setTimestamp(LocalDateTime.now());
			userRepository.save(user);
			return new ResponseEntity<>(Map.of("OTP", otp), HttpStatus.OK);
        }
       return new ResponseEntity<>("The timestamp has not yet passed",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	
	
}

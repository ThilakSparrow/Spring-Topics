package io.spring.security.owasp.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import io.spring.security.owasp.entity.UserDTO;
import io.spring.security.owasp.entity.Users;

@Service
public class MassAssignmentService {
	
	public ResponseEntity<?> signup_v(@RequestBody Users user){
		UserDTO userDto = new UserDTO(user.getUsername(),user.getRole(),user.getCredits());
		return new ResponseEntity<>(userDto,HttpStatus.OK);
	}
	
	public ResponseEntity<?> signup_s(@RequestBody Users user){
		if(user.getCredits()>50) {
			user.setCredits(50);
			return new ResponseEntity<>("Credit limit should not exceed 50",HttpStatus.BAD_REQUEST);
		}
		user.setCredits(50);
		UserDTO userDto = new UserDTO(user.getUsername(),user.getRole(),user.getCredits());
		return new ResponseEntity<>(userDto,HttpStatus.OK);
	}
	

}

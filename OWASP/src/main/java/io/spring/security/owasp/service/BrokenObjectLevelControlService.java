package io.spring.security.owasp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.spring.security.owasp.entity.UserDTO;
import io.spring.security.owasp.entity.Users;
import io.spring.security.owasp.repository.UserRepository;

@Service
public class BrokenObjectLevelControlService {

	@Autowired
	UserRepository userRepository;

	public ResponseEntity<?> showUserDetails_v(String username) {
		Users user = userRepository.findByUsername(username);
		UserDTO userDto = new UserDTO(user.getUsername(), user.getRole(), user.getCredits());
		return new ResponseEntity<>("User Details :" + userDto, HttpStatus.OK);
	}

	public ResponseEntity<?> showUserDetails_s(String username, int token) {
		Users user = userRepository.findByUsername(username);

		UserDTO userDto = new UserDTO(user.getUsername(), user.getRole(), user.getCredits());

		if (user.getToken().equals(token)) {
			return new ResponseEntity<>("User Details :" + userDto, HttpStatus.OK);
		}
		return new ResponseEntity<>("Access Denied ", HttpStatus.UNAUTHORIZED);
	}

}

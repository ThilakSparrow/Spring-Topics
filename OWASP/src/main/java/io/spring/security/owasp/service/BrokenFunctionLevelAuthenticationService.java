package io.spring.security.owasp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.spring.security.owasp.entity.UserDTO;
import io.spring.security.owasp.entity.Users;
import io.spring.security.owasp.repository.UserRepository;

@Service
public class BrokenFunctionLevelAuthenticationService {

	private static final String ADMIN_ROLE = "admin";
	private static final String USER_ROLE = "user";

	@Autowired
	UserRepository userRepository;

	public ResponseEntity<?> showDetails(int id) {
		Users user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));

		UserDTO userDto = new UserDTO(user.getUsername(), user.getRole(), user.getCredits());
		return new ResponseEntity<>("Admin Details :" + userDto, HttpStatus.OK);
	}

	public ResponseEntity<?> showAdminDetails(int id) {
		Users user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));

		if (ADMIN_ROLE.equals(user.getRole().toLowerCase())) {
			UserDTO userDto = new UserDTO(user.getUsername(), user.getRole(), user.getCredits());
			return new ResponseEntity<>("Admin Details :" + userDto, HttpStatus.OK);
		}

		return new ResponseEntity<>("Access Denied : you are not an admin", HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<?> showUserDetails(int id) {

		Users user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));

		if (USER_ROLE.equals(user.getRole().toLowerCase())) {
			UserDTO userDto = new UserDTO(user.getUsername(), user.getRole(), user.getCredits());
			return new ResponseEntity<>("User Details :" + userDto, HttpStatus.OK);
		}
		return new ResponseEntity<>("Access Denied : you are not an user", HttpStatus.BAD_REQUEST);
	}

}

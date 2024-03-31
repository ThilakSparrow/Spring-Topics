package io.spring.security.owasp.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.spring.security.owasp.reqres.StackErrorResponse;

@Service
public class SecurityMisconfigurationService {

	public ResponseEntity<?> stackTrace_v() {

		try {

			throw new ArithmeticException("Divide by zero error");
		} catch (Exception e) {
		
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error occurred. Stack trace:\n" + getStackTraceAsString(e));
		}
	}

	private String getStackTraceAsString(Exception e) {
		StringBuilder sb = new StringBuilder();
		for (StackTraceElement element : e.getStackTrace()) {
			sb.append(element.toString()).append("\n");
		}
		return sb.toString();
	}

	public ResponseEntity<?> stackTrace_s() {
		try {
			// Simulate an error by dividing by zero
			throw new ArithmeticException("Divide by zero error");
		} catch (Exception e) {
			// Return the stack trace as a string in the response
			StackErrorResponse stackErrorResponse = new StackErrorResponse(false,
					"Network Server @ 2 - Malfunctioned , Contact Administrator");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(stackErrorResponse);
		}
	}

}

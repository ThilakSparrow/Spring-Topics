package io.spring.security.exception;

public class InvalidUserDetailsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidUserDetailsException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidUserDetailsException(String message) {
		super(message);
	}
}

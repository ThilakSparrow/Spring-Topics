package io.spring.security.exception;

public class NoSuchProductFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoSuchProductFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchProductFoundException(String message) {
		super(message);
	}
}

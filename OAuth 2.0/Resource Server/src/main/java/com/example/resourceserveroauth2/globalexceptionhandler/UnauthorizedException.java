package com.example.resourceserveroauth2.globalexceptionhandler;

public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnauthorizedException(String message){
		super(message);
	}

	
}

package com.example.resourceserveroauth2.globalexceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(UnauthorizedException.class)
	public APIResponse handleUnauthorizedException(UnauthorizedException e) {
		APIResponse response = new APIResponse();
		response.setStatus(HttpStatus.UNAUTHORIZED);
		response.setMessage(e.getMessage());
		
		return response;
	}
}

package com.example.resourceserveroauth2.globalexceptionhandler;

import org.springframework.http.HttpStatus;

public class APIResponse {
	
	private HttpStatus status;
	private int code;
	private String message;
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}

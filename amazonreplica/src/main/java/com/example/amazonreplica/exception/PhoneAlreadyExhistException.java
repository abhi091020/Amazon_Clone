package com.example.amazonreplica.exception;

public class PhoneAlreadyExhistException extends RuntimeException{
	
	private String message;
	
	PhoneAlreadyExhistException(String message){
		this.message=message;
	}
	
	public String getMessage() {
		return message;
	}

}

package com.example.amazonreplica.exception;

public class UserAlreadyExhistException extends RuntimeException{
	
	private String message;
	
	public UserAlreadyExhistException(String message){
		this.message=message;
	}
	
	public String getMessage() {
		return message;
	}

}

package com.example.amazonreplica.exception;

public class WrongOTPException extends RuntimeException{
	
private String message;
	
     public WrongOTPException(String message){
		this.message=message;
	}
	
	public String getMessage() {
		return message;
	}

}

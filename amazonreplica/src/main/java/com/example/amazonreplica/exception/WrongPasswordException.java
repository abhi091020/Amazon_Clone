package com.example.amazonreplica.exception;

public class WrongPasswordException extends RuntimeException{
private String message;
	
      public WrongPasswordException(String message){
		this.message=message;
	}
	
	public String getMessage() {
		return message;
	}

}

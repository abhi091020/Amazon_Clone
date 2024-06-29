package com.example.amazonreplica.exception;

public class EmailAlreadyExhistException extends RuntimeException{
	
	private String message;
	
	public EmailAlreadyExhistException(String message){
		this.message=message;		
	}

	public String getMessage() {
		return message;
	}

		

}

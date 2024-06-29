package com.example.amazonreplica.exception;

public class DuplicateDataFoundException extends RuntimeException{
	
   private String message;
	
  public DuplicateDataFoundException(String message){
		this.message=message;		
	}

	public String getMessage() {
		return message;
	}

}

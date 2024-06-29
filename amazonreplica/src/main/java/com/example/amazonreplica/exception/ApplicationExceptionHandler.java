package com.example.amazonreplica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.amazonreplica.util.ResponseStructure;



@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(EmailAlreadyExhistException.class)
	public ResponseEntity<ResponseStructure<String>> handleEmailAlreadyExhistException(EmailAlreadyExhistException ex) {
		ResponseStructure<String> s = new ResponseStructure<String>();
		s.setMessage("User with given email already exhist");
		s.setStatus(HttpStatus.FORBIDDEN.value());
		s.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(s,HttpStatus.FORBIDDEN);
		
	}
	
	@ExceptionHandler(PhoneAlreadyExhistException.class)
	public ResponseEntity<ResponseStructure<String>> handlePhoneAlreadyExhistException(PhoneAlreadyExhistException ex) {
		ResponseStructure<String> s = new ResponseStructure<String>();
		s.setMessage("User with given phone already exhist");
		s.setStatus(HttpStatus.FORBIDDEN.value());
		s.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(s,HttpStatus.FORBIDDEN);
		
	}
	
	@ExceptionHandler(UserAlreadyExhistException.class)
	public ResponseEntity<ResponseStructure<String>> handleUserAlreadyExhistException(UserAlreadyExhistException ex) {
		ResponseStructure<String> s = new ResponseStructure<String>();
		s.setMessage("User already exhist");
		s.setStatus(HttpStatus.CONFLICT.value());
		s.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(s,HttpStatus.CONFLICT);
		
	}
	
	@ExceptionHandler(WrongOTPException.class)
	public ResponseEntity<ResponseStructure<String>> handleWrongOTPException(WrongOTPException ex) {
		ResponseStructure<String> s = new ResponseStructure<String>();
		s.setMessage("Wrong OTP");
		s.setStatus(HttpStatus.FORBIDDEN.value());
		s.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(s,HttpStatus.FORBIDDEN);
		
	}
	
	@ExceptionHandler(WrongPasswordException.class)
	public ResponseEntity<ResponseStructure<String>> handleWrongPasswordException(WrongPasswordException ex) {
		ResponseStructure<String> s = new ResponseStructure<String>();
		s.setMessage("Wrong Password");
		s.setStatus(HttpStatus.FORBIDDEN.value());
		s.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(s,HttpStatus.FORBIDDEN);
		
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleUserNotFoundException(UserNotFoundException ex) {
		ResponseStructure<String> s = new ResponseStructure<String>();
		s.setMessage("User not found");
		s.setStatus(HttpStatus.NOT_FOUND.value());
		s.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(s,HttpStatus.NOT_FOUND);
		
	}

	@ExceptionHandler(java.net.UnknownHostException.class)
	public ResponseEntity<ResponseStructure<String>> handlejavanetUnknownHostException(java.net.UnknownHostException ex) {
		ResponseStructure<String> s = new ResponseStructure<String>();
		s.setMessage("no internet connection");
		s.setStatus(HttpStatus.FORBIDDEN.value());
		s.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(s,HttpStatus.FORBIDDEN);
		
	}
	
	@ExceptionHandler(com.example.amazonreplica.exception.DuplicateDataFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleDuplicateDataFoundException(com.example.amazonreplica.exception.DuplicateDataFoundException ex) {
		ResponseStructure<String> s = new ResponseStructure<String>();
		s.setMessage("duplicate data found");
		s.setStatus(HttpStatus.CONFLICT.value());
		s.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(s,HttpStatus.CONFLICT);
		
	}
	
	
	
	@ExceptionHandler(java.lang.NullPointerException.class)
	public ResponseEntity<ResponseStructure<String>> handlejavalangNullPointerException(java.lang.NullPointerException ex) {
		ResponseStructure<String> s = new ResponseStructure<String>();
		s.setMessage("Session time out");
		s.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
		s.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(s,HttpStatus.SERVICE_UNAVAILABLE);
		
	}
	
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleDataNotFoundException(DataNotFoundException ex) {
		ResponseStructure<String> s = new ResponseStructure<String>();
		s.setMessage("no data found");
		s.setStatus(HttpStatus.NOT_FOUND.value());
		s.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(s,HttpStatus.NOT_FOUND);
		
	}
	
	
}

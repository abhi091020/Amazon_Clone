package com.example.amazonreplica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.amazonreplica.dao.UserDao;
import com.example.amazonreplica.dto.User;
import com.example.amazonreplica.exception.DuplicateDataFoundException;
import com.example.amazonreplica.exception.EmailAlreadyExhistException;
import com.example.amazonreplica.exception.UserAlreadyExhistException;
import com.example.amazonreplica.exception.UserNotFoundException;
import com.example.amazonreplica.exception.WrongOTPException;
import com.example.amazonreplica.exception.WrongPasswordException;
import com.example.amazonreplica.util.ResponseStructure;

@Service
public class UserService {
	
	private String OTP;
	
	private User user;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private EmailSenderService mainSender;
	

	ResponseStructure<User> response;
	
	public ResponseEntity<ResponseStructure<User>> userRegister( User user) {
		if(userDao.getByUserEmail(user.getUserEmail()) !=null) {
			throw new UserAlreadyExhistException("user with given email aready exhist");
		}
		OTP=mainSender.GenerateOtp(user.getUserEmail());
		this.user=user;
		
		response = new ResponseStructure<User>();
		response.setData(null);
		response.setMessage("otp send to email for registration");
		response.setStatus(HttpStatus.OK.value());
		
		return new ResponseEntity<ResponseStructure<User>>(response,HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<User>> register(String OTP) {
		// TODO Auto-generated method stub
		if(OTP ==null) {
			throw new WrongOTPException("Wrong OTP Enterd");
		}
		if(this.OTP.equals(OTP)) {
			user.setToken(user.getUserEmail()+"@"+(10000000+(long)(Math.random()*90000000)));
			response = new ResponseStructure<User>();
			try {
				response.setData(userDao.register(user));
			} catch (Exception e) {
				// TODO: handle exception
				throw new DuplicateDataFoundException("duplicate data found");
			}
			response.setMessage("user Resgister Succesfully");
			response.setStatus(HttpStatus.ACCEPTED.value());
			
			return new ResponseEntity<ResponseStructure<User>>(response,HttpStatus.ACCEPTED);
		}
		else {
			throw new WrongOTPException("Wrong OTP Enterd");
		}
		
	}

	public ResponseEntity<ResponseStructure<User>> Signin(String email, String password) {
		// TODO Auto-generated method stub
		User user = userDao.getByUserEmail(email);
		if(user != null) {
			System.out.println(user.getUserEmail()+password);
			
			if(user.getPassword().equals(password)) {
				response = new ResponseStructure<User>();
				response.setData(user);
				response.setMessage("user Login Succesfully");
				response.setStatus(HttpStatus.ACCEPTED.value());
				
				return new ResponseEntity<ResponseStructure<User>>(response,HttpStatus.ACCEPTED);
			}
			{
				throw new WrongPasswordException("wrong password enterd");
			}
		}
		else {
			throw new UserNotFoundException("no user found");
		}
	}

	public ResponseEntity<String> setPassword(String email) {
		// TODO Auto-generated method stub
		
		User user = userDao.getByUserEmail(email);
		if (user !=null) {
			OTP=mainSender.GenerateOtp(user.getUserEmail());
			this.user=user;
			return new ResponseEntity<String>("otp send for validation",HttpStatus.OK);
		} else {
			throw new UserNotFoundException("no user Found");
		}
		
	}

	public ResponseEntity<ResponseStructure<User>> signinValidate(String oTP2,String password) {
		// TODO Auto-generated method stub
		if(OTP ==null) {
			throw new WrongOTPException("Wrong OTP Enterd");
		}
		if(this.OTP.equals(oTP2)) {
			user.setPassword(password);
			response = new ResponseStructure<User>();
			response.setData(userDao.register(user));
			response.setMessage("user Signin Succesfully");
			response.setStatus(HttpStatus.ACCEPTED.value());
			
			return new ResponseEntity<ResponseStructure<User>>(response,HttpStatus.ACCEPTED);
		}
		else {
			throw new WrongOTPException("Wrong OTP Enterd");
		}
	}

}

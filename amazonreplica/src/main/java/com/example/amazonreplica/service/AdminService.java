package com.example.amazonreplica.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.amazonreplica.dao.AdminDao;
import com.example.amazonreplica.dto.Admin;
import com.example.amazonreplica.exception.DuplicateDataFoundException;
import com.example.amazonreplica.exception.UserAlreadyExhistException;
import com.example.amazonreplica.exception.UserNotFoundException;
import com.example.amazonreplica.exception.WrongOTPException;
import com.example.amazonreplica.exception.WrongPasswordException;
import com.example.amazonreplica.util.ResponseStructure;

@Service
public class AdminService {
	
    private String OTP;
	
	private Admin admin;
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private EmailSenderService mainSender;
	

	ResponseStructure<Admin> response;
	
	public ResponseEntity<ResponseStructure<Admin>> adminRegister( Admin admin) {
		if(adminDao.getByUserEmail(admin.getAdminEmail())!=null) {
			throw new UserAlreadyExhistException("admin already exist");
		}
		OTP=mainSender.GenerateOtp(admin.getAdminEmail());
		this.admin=admin;
		
		response = new ResponseStructure<Admin>();
		response.setData(null);
		response.setMessage("otp send to email for registration");
		response.setStatus(HttpStatus.OK.value());
		
		return new ResponseEntity<ResponseStructure<Admin>>(response,HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Admin>> register(String OTP) {
		// TODO Auto-generated method stub
		if(OTP ==null) {
			throw new WrongOTPException("Wrong OTP Enterd");
		}
		
		if(this.OTP.equals(OTP)) {
			admin.setToken(admin.getAdminEmail()+"@"+UUID.randomUUID().toString());
			System.out.println(admin);
			response = new ResponseStructure<Admin>();
			try {
				response.setData(adminDao.register(admin));
			} catch (Exception e) {
				// TODO: handle exception
				throw new DuplicateDataFoundException("duplicate data found"+e);
			}
			response.setMessage("user Resgister Succesfully");
			response.setStatus(HttpStatus.ACCEPTED.value());
			
			return new ResponseEntity<ResponseStructure<Admin>>(response,HttpStatus.ACCEPTED);
		}
		else {
			throw new WrongOTPException("Wrong OTP Enterd");
		}
		
	}

	public ResponseEntity<ResponseStructure<Admin>> Signin(String email, String password) {
		// TODO Auto-generated method stub
		Admin admin = adminDao.getByUserEmail(email);
		if(admin != null) {
			if(admin.getPassword().equals(password)) {
				response = new ResponseStructure<Admin>();
				response.setData(admin);
				response.setMessage("user Login Succesfully");
				response.setStatus(HttpStatus.ACCEPTED.value());
				
				return new ResponseEntity<ResponseStructure<Admin>>(response,HttpStatus.ACCEPTED);
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
		
		Admin admin = adminDao.getByUserEmail(email);
		if (admin !=null) {
			OTP=mainSender.GenerateOtp(admin.getAdminEmail());
			this.admin=admin;
			return new ResponseEntity<String>("otp send for validation",HttpStatus.OK);
		} else {
			throw new UserNotFoundException("no user Found");
		}
		
	}

	public ResponseEntity<ResponseStructure<Admin>> signinValidate(String oTP2,String password) {
		// TODO Auto-generated method stub
		if(OTP ==null) {
			throw new WrongOTPException("Wrong OTP Enterd");
		}
		if(this.OTP.equals(oTP2)) {
			admin.setPassword(password);
			response = new ResponseStructure<Admin>();
			response.setData(adminDao.register(admin));
			response.setMessage("user Signin Succesfully");
			response.setStatus(HttpStatus.ACCEPTED.value());
			
			return new ResponseEntity<ResponseStructure<Admin>>(response,HttpStatus.ACCEPTED);
		}
		else {
			throw new WrongOTPException("Wrong OTP Enterd");
		}
	}


}

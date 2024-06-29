package com.example.amazonreplica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	public void sendMail(String toMail,String body,String Subject) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject(Subject);
		message.setText(body);
		message.setTo(toMail);
		message.setFrom("abhishekbhore9@gmail.com");
		
		mailSender.send(message);
		System.out.println("suucceful");
		
	}
	
	public String GenerateOtp(String email) {
		String OTP = String.valueOf(1000+(int)(Math.random()*9000));
		
		String Message="Dear "+email
				+ " Thank you for registering with Amazon. To complete your registration, please enter the One Time Pin (OTP) provided below : "	+ " OTP : "+OTP
				+ " Please keep this OTP confidential and do not share it with anyone. If you have any questions or need further assistance, feel free to reach out to our support team."
				+ "Best regards,"
				+ "Team Amazon";
		
		sendMail(email, Message, "otp for registration");
		return OTP;
		
	}
	
	
	
	

}

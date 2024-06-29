package com.example.amazonreplica.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.amazonreplica.dto.User;
import com.example.amazonreplica.repo.UserRepo;

@Repository
public class UserDao {

	@Autowired
	private UserRepo repo;
	
	public User register(User user) {
		// TODO Auto-generated method stub
		return repo.save(user);
	
	}
		
	

	public User getByUserEmail(String Email) {
		// TODO Auto-generated method stub
		return repo.getByUserEmail( Email);
	}



	public User getByToken(String token) {
		// TODO Auto-generated method stub
		return repo.getByToken(token);
	}

}

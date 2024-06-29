package com.example.amazonreplica.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.amazonreplica.dto.Admin;
import com.example.amazonreplica.dto.User;
import com.example.amazonreplica.repo.AdminRepo;
import com.example.amazonreplica.repo.UserRepo;

@Repository
public class AdminDao {
	
	@Autowired
	private AdminRepo repo;
	
	public Admin register(Admin admin) {
		// TODO Auto-generated method stub
		return repo.save(admin);
	
	}
		
	

	public Admin getByUserEmail(String Email) {
		// TODO Auto-generated method stub
		return repo.getByAdminEmail( Email);
	}



	public Admin getByToken(String token) {
		// TODO Auto-generated method stub
		return repo.getByToken(token);
	}


}

package com.example.amazonreplica.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.amazonreplica.dto.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	User getByUserEmail(String email);

	@Query("select u from User u where u.Token=?1")
	User getByToken(String token);

}

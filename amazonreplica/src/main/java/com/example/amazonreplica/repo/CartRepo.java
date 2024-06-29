package com.example.amazonreplica.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.amazonreplica.dto.Cart;
import com.example.amazonreplica.dto.User;

public interface CartRepo extends JpaRepository<Cart, Integer>{

	@Query("select c from Cart c where c.user=?1")
	Cart getByUser(String token);

}

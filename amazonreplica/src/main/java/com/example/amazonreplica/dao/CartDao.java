package com.example.amazonreplica.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.amazonreplica.dto.Cart;
import com.example.amazonreplica.dto.User;
import com.example.amazonreplica.repo.CartRepo;

@Repository
public class CartDao {

	@Autowired
	private CartRepo cRepo;
	
	public Cart add(Cart cart) {
		// TODO Auto-generated method stub
		return cRepo.save(cart);
	}

	public Cart getById(int id) {
		// TODO Auto-generated method stub
		return cRepo.getReferenceById(id);
	}

	public void delete(Cart c) {
		// TODO Auto-generated method stub
		cRepo.delete(c);
	}

	public Cart getByUser(String u) {
		// TODO Auto-generated method stub
		return cRepo.getByUser(u);
	}

}

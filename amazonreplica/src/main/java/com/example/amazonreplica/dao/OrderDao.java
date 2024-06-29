package com.example.amazonreplica.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.example.amazonreplica.dto.OrderDetail;
import com.example.amazonreplica.repo.OrderRepo;
import com.example.amazonreplica.util.ResponseStructure;

@Repository
public class OrderDao {

	@Autowired
	private OrderRepo repo;
	
	public OrderDetail add(OrderDetail order) {
		// TODO Auto-generated method stub
		return repo.save(order);
	}

	public OrderDetail getById(int id) {
		// TODO Auto-generated method stub
		return repo.getReferenceById(id);
	}

	public void remove(OrderDetail c) {
		// TODO Auto-generated method stub
		repo.delete(c);
	}

//	public Object[] getAdminOrder(String adminId) {
//		// TODO Auto-generated method stub
//		return repo.getAdminOrder(adminId);
//	}
	
	

}

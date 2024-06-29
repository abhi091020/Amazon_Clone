package com.example.amazonreplica.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import com.example.amazonreplica.dto.OrderDetail;
import com.example.amazonreplica.util.ResponseStructure;

public interface OrderRepo extends JpaRepository<OrderDetail, Integer>{

//	@Query("select o.user,p from OrderDetail o JOIN o.products p where p.adminToken=?1")
//	Object[] getAdminOrder(String adminId);

}

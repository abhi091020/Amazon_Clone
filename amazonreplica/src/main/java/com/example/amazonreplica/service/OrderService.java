package com.example.amazonreplica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.amazonreplica.dao.CartDao;
import com.example.amazonreplica.dao.OrderDao;
import com.example.amazonreplica.dao.UserDao;
import com.example.amazonreplica.dto.Cart;
import com.example.amazonreplica.dto.OrderDetail;
import com.example.amazonreplica.dto.Product;
import com.example.amazonreplica.dto.User;
import com.example.amazonreplica.exception.DataNotFoundException;
import com.example.amazonreplica.exception.UserNotFoundException;
import com.example.amazonreplica.util.ResponseStructure;

@Service
public class OrderService {
	
	@Autowired
	private OrderDao oDao;
	
	@Autowired
	private CartDao cDao;
	
	@Autowired
	private UserDao uDao;
	
	ResponseStructure<Cart> response;

	public ResponseEntity<ResponseStructure<Cart>> addCart(Cart cart, String token) {
		// TODO Auto-generated method stub
		User u=uDao.getByToken(token);
		if(u==null) {
			throw new UserNotFoundException("no user with given token");
		}
		Cart c=cDao.getByUser(token);
		if(c!=null) {
			cart.getProducts().addAll(c.getProducts());
		}
		else {
			cart.setUser(token);
		}
		response = new ResponseStructure<Cart>();
		response.setData(cDao.add(cart));
		response.setMessage("cart added successfully");
		response.setStatus(HttpStatus.ACCEPTED.value());
		
		return new ResponseEntity<ResponseStructure<Cart>>(response,HttpStatus.ACCEPTED);
	}

	public ResponseEntity<ResponseStructure<Cart>> updateCart(Cart cart) {
		// TODO Auto-generated method stub
		Cart c = cDao.getById(cart.getId());
		if(c == null) {
			throw new DataNotFoundException();
		}
		response = new ResponseStructure<Cart>();
		response.setData(cDao.add(cart));
		response.setMessage("cart updated successfully");
		response.setStatus(HttpStatus.ACCEPTED.value());
		
		return new ResponseEntity<ResponseStructure<Cart>>(response,HttpStatus.ACCEPTED);
	}

	public ResponseEntity<ResponseStructure<Cart>> removeCart(int cartId) {
		// TODO Auto-generated method stub
		Cart c = cDao.getById(cartId);
		if(c == null) {
			throw new DataNotFoundException();
		}
		cDao.delete(c);
		response = new ResponseStructure<Cart>();
		response.setData(c);
		response.setMessage("cart deleted successfully");
		response.setStatus(HttpStatus.ACCEPTED.value());
		
		return new ResponseEntity<ResponseStructure<Cart>>(response,HttpStatus.ACCEPTED);
	}
	
	//order related operations

	public ResponseEntity<ResponseStructure<OrderDetail>> addOrder(OrderDetail order) {
		// TODO Auto-generated method stub
		ResponseStructure<OrderDetail> response = new ResponseStructure<OrderDetail>();
		response.setData(oDao.add(order));
		response.setMessage("order added successfully");
		response.setStatus(HttpStatus.ACCEPTED.value());
		
		return new ResponseEntity<ResponseStructure<OrderDetail>>(response,HttpStatus.ACCEPTED);
	}

	public ResponseEntity<ResponseStructure<OrderDetail>> updateOrder(OrderDetail order) {
		// TODO Auto-generated method stub
		OrderDetail c = oDao.getById(order.getId());
		if(c == null) {
			throw new DataNotFoundException();
		}
		
		ResponseStructure<OrderDetail> response = new ResponseStructure<OrderDetail>();
		response.setData(oDao.add(order));
		response.setMessage("order updated successfully");
		response.setStatus(HttpStatus.ACCEPTED.value());
		
		return new ResponseEntity<ResponseStructure<OrderDetail>>(response,HttpStatus.ACCEPTED);
	
	}

	public ResponseEntity<ResponseStructure<OrderDetail>> removeOrder(int orderId) {
		// TODO Auto-generated method stub
		OrderDetail c = oDao.getById(orderId);
		if(c == null) {
			throw new DataNotFoundException();
		}
		oDao.remove(c);
		ResponseStructure<OrderDetail> response = new ResponseStructure<OrderDetail>();
		response.setData(c);
		response.setMessage("order deleted successfully");
		response.setStatus(HttpStatus.ACCEPTED.value());
		
		return new ResponseEntity<ResponseStructure<OrderDetail>>(response,HttpStatus.ACCEPTED);
	
		
	}

	public ResponseEntity<ResponseStructure<Cart>> getCart(int orderId) {
		// TODO Auto-generated method stub
		Cart c = cDao.getById(orderId);
		if(c == null) {
			throw new DataNotFoundException();
		}
		
		ResponseStructure<Cart> response = new ResponseStructure<Cart>();
		response.setData(c);
		response.setMessage("order fetch successfully");
		response.setStatus(HttpStatus.FOUND.value());
		
		return new ResponseEntity<ResponseStructure<Cart>>(response,HttpStatus.FOUND);
	
	}

//	public ResponseEntity<ResponseStructure<Object[]>> getAdminOrder(String adminId) {
//		// TODO Auto-generated method stub
//		ResponseStructure<Object[]> response = new ResponseStructure<Object[]>();
//		response.setData(oDao.getAdminOrder(adminId));
//		response.setMessage("order fetch successfully");
//		response.setStatus(HttpStatus.FOUND.value());
//		
//		return new ResponseEntity<ResponseStructure<Object[]>>(response,HttpStatus.FOUND);
//	
//		
//	}

}

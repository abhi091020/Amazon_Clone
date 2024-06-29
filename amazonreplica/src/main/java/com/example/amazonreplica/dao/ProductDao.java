package com.example.amazonreplica.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.amazonreplica.dto.Product;
import com.example.amazonreplica.repo.ProductRepo;

@Repository
public class ProductDao {

	@Autowired
	private ProductRepo repo;
	
	public Product add(Product pro) {
	// TODO Auto-generated method stub
		return repo.save(pro);
	}
	public Product getById(int id) {
		// TODO Auto-generated method stub
		return repo.getProductById(id);
	}
	public void remove(Product p) {
		// TODO Auto-generated method stub
		 repo.delete(p);;
	}
	public List<Product> getByCategory(String cat) {
		// TODO Auto-generated method stub
		return repo.getByCategory(cat);
	}
	public List<Product> getBySubCategory(String cat) {
		// TODO Auto-generated method stub
		return repo.getBySubCategory(cat);
	}
	public List<Product> getProduct(String str) {
		// TODO Auto-generated method stub
		return repo.getProduct(str);
	}
	public List<Product> getAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

}

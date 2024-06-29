package com.example.amazonreplica.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.amazonreplica.dto.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{

	@Query("select p from Product p where p.category=?1")
	List<Product> getByCategory(String cat);


	List<Product> getBySubCategory(String cat);

	@Query("SELECT p FROM Product p where p.productName=?1 OR "
			+ "p.category=?1 OR "
			+ "p.subCategory=?1")
	List<Product> getProduct(String str);

	
	@Query("select p from Product p where p.id=?1")
	Product getProductById(Integer id);

	

}

package com.example.amazonreplica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.amazonreplica.dao.AdminDao;
import com.example.amazonreplica.dao.ProductDao;
import com.example.amazonreplica.dao.UserDao;
import com.example.amazonreplica.dto.Admin;
import com.example.amazonreplica.dto.Image;
import com.example.amazonreplica.dto.Product;
import com.example.amazonreplica.dto.User;
import com.example.amazonreplica.exception.DataNotFoundException;
import com.example.amazonreplica.exception.UserNotFoundException;
import com.example.amazonreplica.util.ResponseStructure;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private AdminDao adminDao;
	
	@Value("${project.image}")
	private String path;
	
	
	@Autowired
	private FileService fileService;
	
	ResponseStructure<Product> response;
	
	

	public ResponseEntity<ResponseStructure<Product>> addProduct(Product product, String token) {
		// TODO Auto-generated method stub
		Admin admin = adminDao.getByToken(token);
		if (admin == null) {
			throw new UserNotFoundException("wrong token");
		}
		
		product.setAdminToken(token);;
		response = new ResponseStructure<Product>();
		response.setData(productDao.add(product));
		response.setMessage("product added successfully");
		response.setStatus(HttpStatus.OK.value());
		
		return new ResponseEntity<ResponseStructure<Product>>(response,HttpStatus.OK);
		
	}

	public ResponseEntity<ResponseStructure<Product>> addImage(MultipartFile[] files,int id) {
		// TODO Auto-generated method stub
		Product product  = productDao.getById(id);
		List<Image> i =fileService.uploadFiles(path, files);
		if (!i.isEmpty()) {
			product.setSubImages(i);
		}
		response = new ResponseStructure<Product>();
		response.setData(productDao.add(product));
		response.setMessage("product updates successfully");
		response.setStatus(HttpStatus.ACCEPTED.value());
		
		return new ResponseEntity<ResponseStructure<Product>>(response,HttpStatus.ACCEPTED);
		
	}

	public ResponseEntity<ResponseStructure<String>> removeProduct(int id) {
		// TODO Auto-generated method stub
		Product p = productDao.getById(id);
		
		if (p != null) {
			for (Image tg:p.getSubImages()) {
				
				fileService.remove(tg.getId());
			
			}
			
			productDao.remove(p);
			ResponseStructure<String> response = new ResponseStructure<String>();
			response.setData("data deleted");
			response.setMessage("product deleted successfully");
			response.setStatus(HttpStatus.OK.value());
			
			return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.OK);
			
		} else {
                throw new UserNotFoundException("no user found");
		}
	}

	public ResponseEntity<ResponseStructure<Product>> removeImages(int[] ids,int pid) {
		// TODO Auto-generated method stub
		Product product = productDao.getById(pid);
		if (product != null) {
			for (Integer integer : ids) {
				if (product.getSubImages().removeIf(pro->(pro.getId()==integer))) {
					fileService.remove(integer);
				}
			}
		}
		else {
			throw new DataNotFoundException();
		}
		
		ResponseStructure<Product> response = new ResponseStructure<Product>();
		response.setData(productDao.add(product));
		response.setMessage("images deleted successfully");
		response.setStatus(HttpStatus.OK.value());
		
		return new ResponseEntity<ResponseStructure<Product>>(response,HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Product>> getProductById(int id) {
		// TODO Auto-generated method stub
		Product product = productDao.getById(id);
		if (product != null) {
		 response = new ResponseStructure<Product>();
		response.setData(product);
		response.setMessage("data fetch succefull");
		response.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Product>>(response,HttpStatus.OK);
		
		}else {
			throw new DataNotFoundException();
		}
		
		
	}

	public ResponseEntity<ResponseStructure<List<Product>>> getProductByCategoty(String cat) {
		// TODO Auto-generated method stub
		List<Product> product = productDao.getByCategory(cat);
		ResponseStructure<List<Product>> response = new ResponseStructure<List<Product>>();
		if (!product.isEmpty()) {
		
		response.setData(product);
		response.setMessage("data fetch succefull");
		response.setStatus(HttpStatus.OK.value());
		}else {
			throw new DataNotFoundException();
		}
		
		return new ResponseEntity<ResponseStructure<List<Product>>>(response,HttpStatus.OK);
		
	}

	public ResponseEntity<ResponseStructure<List<Product>>> getProductBySubCategoty(String cat) {
		// TODO Auto-generated method stub
		List<Product> product = productDao.getBySubCategory(cat);
		ResponseStructure<List<Product>> response = new ResponseStructure<List<Product>>();
		if (!product.isEmpty()) {
		
		response.setData(product);
		response.setMessage("data fetch succefull");
		response.setStatus(HttpStatus.OK.value());
		}else {
			throw new DataNotFoundException();
		}
		
		return new ResponseEntity<ResponseStructure<List<Product>>>(response,HttpStatus.OK);
		
	}

	public ResponseEntity<ResponseStructure<List<Product>>> getProduct(String str) {
		// TODO Auto-generated method stub
		List<Product> product = productDao.getByCategory(str);
		ResponseStructure<List<Product>> response = new ResponseStructure<List<Product>>();
		if (!product.isEmpty()) {
		
		response.setData(product);
		response.setMessage("data fetch succefull");
		response.setStatus(HttpStatus.OK.value());
		}else {
			throw new DataNotFoundException();
		}
		
		return new ResponseEntity<ResponseStructure<List<Product>>>(response,HttpStatus.OK);
		
	}

	public ResponseEntity<ResponseStructure<List<Product>>> getAll() {
		// TODO Auto-generated method stub
		List<Product> product = productDao.getAll();
		ResponseStructure<List<Product>> response = new ResponseStructure<List<Product>>();
		if (!product.isEmpty()) {
		
		response.setData(product);
		response.setMessage("data fetch succefull");
		response.setStatus(HttpStatus.OK.value());
		}else {
			throw new DataNotFoundException();
		}
		
		return new ResponseEntity<ResponseStructure<List<Product>>>(response,HttpStatus.OK);
	}

}

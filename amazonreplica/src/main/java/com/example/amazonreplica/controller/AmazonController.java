package com.example.amazonreplica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.amazonreplica.dto.Admin;
import com.example.amazonreplica.dto.Cart;
import com.example.amazonreplica.dto.OrderDetail;
import com.example.amazonreplica.dto.Product;
import com.example.amazonreplica.dto.User;
import com.example.amazonreplica.exception.WrongOTPException;
import com.example.amazonreplica.service.AdminService;
import com.example.amazonreplica.service.EmailSenderService;
import com.example.amazonreplica.service.OrderService;
import com.example.amazonreplica.service.ProductService;
import com.example.amazonreplica.service.UserService;
import com.example.amazonreplica.util.IdArrays;
import com.example.amazonreplica.util.ResponseStructure;

@RestController
public class AmazonController {
	

	
	@Autowired
	private UserService uService;
	
	@Autowired
	private AdminService aService;
	
	@Autowired
	private OrderService oService;
	
	@Autowired
	private ProductService pService;
	
	@PostMapping("user/register")
	public void userRegister(@RequestBody User user) {
		uService.userRegister(user);
	}
	
	@PostMapping("user/register/validate")
	public ResponseEntity<ResponseStructure<User>> userValidate(@RequestParam String OTP) {
		return uService.register(OTP);
	}
	
	@PostMapping("user/signin")
	public ResponseEntity<ResponseStructure<User>> userSignin(@RequestParam String Email,@RequestParam String password) {
		return uService.Signin(Email,password);
	}
	
	@PostMapping("user/signin/forgot/password")
	public ResponseEntity<String> userPassword(@RequestParam String Email) {
		return uService.setPassword(Email);
	}
	
	@PostMapping("user/signin/validate")
	public ResponseEntity<ResponseStructure<User>> userSigninValidate(@RequestParam String OTP,@RequestParam String password) {
		return uService.signinValidate(OTP,password);
	}
	
	
	//Admin data
	
	@PostMapping("admin/register")
	public void adminRegister(@RequestBody Admin user) {
		aService.adminRegister(user);
	}
	
	@PostMapping("admin/register/validate")
	public ResponseEntity<ResponseStructure<Admin>> adminValidate(@RequestParam String OTP) {
		return aService.register(OTP);
	}
	
	@PostMapping("admin/signin")
	public ResponseEntity<ResponseStructure<Admin>> adminSignin(@RequestParam String Email,@RequestParam String password) {
		return aService.Signin(Email,password);
	}
	
	@PostMapping("admin/signin/password")
	public ResponseEntity<String> adminPassword(@RequestParam String Email) {
		return aService.setPassword(Email);
	}
	
	@PostMapping("admin/signin/validate")
	public ResponseEntity<ResponseStructure<Admin>> adminSigninValidate(@RequestParam String OTP,@RequestParam String password) {
		return aService.signinValidate(OTP,password);
	}
	
	
	//product mapping
	
	@PostMapping(value = "product/add")
	public ResponseEntity<ResponseStructure<Product>> addProduct(@RequestBody Product product,@RequestParam String token) {
		return pService.addProduct(product,token);
	}
	
	
	
	@PostMapping(value = "product/add/image",consumes = "multipart/form-data")
	public ResponseEntity<ResponseStructure<Product>> addProduct(@RequestParam(value="files") MultipartFile[] files,@RequestParam(value = "pid") int pid) {
		System.out.println("here");
		return pService.addImage(files,pid);
	}
	
	@PostMapping(value = "product/update")
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestBody Product product,@RequestParam String token) {
		return pService.addProduct(product,token);
	}
	
	@PostMapping(value = "product/remove")
	public ResponseEntity<ResponseStructure<String>> removeProduct(@RequestParam int id) {
		
		return pService.removeProduct(id);
	}
	
	@PostMapping(value = "product/remove/image")
	public ResponseEntity<ResponseStructure<Product>> removeProduct(@RequestBody IdArrays id,@RequestParam int pid) {
		
		return pService.removeImages(id.getArr(),pid);
	}
	
	@GetMapping(value = "product/get/id")
	public ResponseEntity<ResponseStructure<Product>> getProduct(@RequestParam int id) {
		System.out.println("here");
		return pService.getProductById(id);
	}
	
	@GetMapping(value = "product/get/category")
	public ResponseEntity<ResponseStructure<List<Product>>> getProduct(@RequestParam String cat) {
		
		return pService.getProductByCategoty(cat);
	}
	
	@GetMapping(value = "product/get/subCategory")
	public ResponseEntity<ResponseStructure<List<Product>>> getProductSub(@RequestParam String cat) {
		
		return pService.getProductBySubCategoty(cat);
	}
	
	@GetMapping(value = "product/get")
	public ResponseEntity<ResponseStructure<List<Product>>> getProductSearch(@RequestParam String str) {
		
		return pService.getProduct(str);
	}
	
	
	@GetMapping(value = "product/get/all")
	public ResponseEntity<ResponseStructure<List<Product>>> getProduct() {
	
		return pService.getAll();
	}
	//cart related operations
	
	@PostMapping(value = "cart/add")
	public ResponseEntity<ResponseStructure<Cart>> addCart(@RequestBody Cart cart,String token) {
		
		return oService.addCart(cart,token);
	}
	
	@PostMapping(value = "cart/update")
	public ResponseEntity<ResponseStructure<Cart>> updateCart(@RequestBody Cart cart) {
		
		return oService.updateCart(cart);
	}
	
	@PostMapping(value = "cart/remove")
	public ResponseEntity<ResponseStructure<Cart>> removeCart(@RequestParam int cartId) {
		
		return oService.removeCart(cartId);
	}
	
	@PostMapping(value = "cart/get")
	public ResponseEntity<ResponseStructure<Cart>> getCart(@RequestParam int cartId) {
		
		return oService.getCart(cartId);
	}
	
	
	//order related operations 
	
	@PostMapping(value = "order/add")
	public ResponseEntity<ResponseStructure<OrderDetail>> addOrder(@RequestBody OrderDetail order) {
		
		return oService.addOrder(order);
	}
	
	@PostMapping(value = "order/update")
	public ResponseEntity<ResponseStructure<OrderDetail>> updateOrder(@RequestBody OrderDetail order) {
		
		return oService.updateOrder(order);
	}
	
	@PostMapping(value = "order/remove")
	public ResponseEntity<ResponseStructure<OrderDetail>> removeOrder(@RequestParam int orderId) {
		
		return oService.removeOrder(orderId);
	}
	
//	@PostMapping(value = "order/get/admin")
//	public ResponseEntity<ResponseStructure<Object[]>> getAdminOrder(@RequestParam String adminId) {
//		
//		return oService.getAdminOrder(adminId);
//	}
	
	
}

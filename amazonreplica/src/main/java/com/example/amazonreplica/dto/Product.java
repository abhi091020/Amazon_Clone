package com.example.amazonreplica.dto;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@NotBlank
	private String productName;
	
	@NotNull
	@NotBlank
	private String category;
	
	@NotBlank
	@NotNull
	private String subCategory;
	
	@NotNull
	@NotBlank
	private String brand;
	
	@NotNull
	@NotBlank
	private String shortDesc;
	
	@NotNull
	@Column(length = 51000)
	private String longDesc;
	
	@NotNull
	private double prize;
	
	@NotNull
	private double discount;
	
	@NotNull
	private double costPrize;
	
	@NotNull
	@NotBlank
	private String offers;
	
	@NotNull
	private int stock;
	
	@NotNull
	private int delTime;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Image> subImages;
	
	private String adminToken;


	

	public String getAdminToken() {
		return adminToken;
	}


	public void setAdminToken(String adminToken) {
		this.adminToken = adminToken;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getSubCategory() {
		return subCategory;
	}


	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getShortDesc() {
		return shortDesc;
	}


	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}


	public String getLongDesc() {
		return longDesc;
	}


	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}


	public double getPrize() {
		return prize;
	}


	public void setPrize(double prize) {
		this.prize = prize;
	}


	public double getDiscount() {
		return discount;
	}


	public void setDiscount(double discount) {
		this.discount = discount;
	}


	public double getCostPrize() {
		return costPrize;
	}


	public void setCostPrize(double costPrize) {
		this.costPrize = costPrize;
	}


	public String getOffers() {
		return offers;
	}


	public void setOffers(String offers) {
		this.offers = offers;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public int getDelTime() {
		return delTime;
	}


	public void setDelTime(int delTime) {
		this.delTime = delTime;
	}


	public List<Image> getSubImages() {
		return subImages;
	}


	public void setSubImages(List<Image> list) {
		this.subImages = list;
	}
	
	
	
	
	

}

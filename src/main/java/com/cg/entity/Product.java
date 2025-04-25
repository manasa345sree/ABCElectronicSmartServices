package com.cg.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {

    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String modelNumber;

    private String productName;
    private String productCategoryName;

    private LocalDate dateofPurchase;
    private Integer warrantyYears;
    private LocalDate warrantyDate;
    
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCategoryName() {
		return productCategoryName;
	}
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}
	public LocalDate getDateofPurchase() {
		return dateofPurchase;
	}
	public void setDateofPurchase(LocalDate dateofPurchase) {
		this.dateofPurchase = dateofPurchase;
	}
	public Integer getWarrantyYears() {
		return warrantyYears;
	}
	public void setWarrantyYears(Integer warrantyYears) {
		this.warrantyYears = warrantyYears;
	}
	public LocalDate getWarrantyDate() {
		return warrantyDate;
	}
	public void setWarrantyDate(LocalDate warrantyDate) {
		this.warrantyDate = warrantyDate;
	}


}

package com.cg.service;

import java.util.List;

import com.cg.entity.Complaint;
import com.cg.entity.Engineer;
import com.cg.entity.Product;

public interface IProductService {

    void addProduct(Product product);

    void removeProducts(String modelNumber);

    List<Product> getProduct(String modelNumber);

    void updateProductWarranty(String modelNumber);

    List<Complaint> getProductComplaints(String modelNumber);

    List<Engineer> getEngineersByProduct(String modelNumber);
}

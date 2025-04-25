package com.cg.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.Exception.ProductNotFoundException;
import com.cg.entity.Complaint;
import com.cg.entity.Engineer;
import com.cg.entity.Product;
import com.cg.repository.ComplaintRepository;
import com.cg.repository.EngineerRepository;
import com.cg.repository.ProductRepository;
import com.cg.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private EngineerRepository engineerRepository;

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void removeProducts(String modelNumber) {
        Product product = productRepository.findById(modelNumber)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with model number: " + modelNumber));
        productRepository.delete(product);
    }

    @Override
    public List<Product> getProduct(String modelNumber) {
        return productRepository.findByModelNumber(modelNumber);
    }

    @Override
    public void updateProductWarranty(String modelNumber) {
        Product product = productRepository.findById(modelNumber)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with model number: " + modelNumber));

        product.setWarrantyDate(product.getDateofPurchase().plusYears(product.getWarrantyYears()));
        productRepository.save(product);
    }

    @Override
    public List<Complaint> getProductComplaints(String modelNumber) {
        return complaintRepository.findByProductModelNumber(modelNumber);
    }

    @Override
    public List<Engineer> getEngineersByProduct(String modelNumber) {
        Product product = productRepository.findById(modelNumber)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with model number: " + modelNumber));
        return engineerRepository.findByDomain(product.getProductCategoryName());
    }
}

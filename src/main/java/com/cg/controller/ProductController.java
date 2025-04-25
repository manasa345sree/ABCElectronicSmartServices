package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cg.entity.Complaint;
import com.cg.entity.Engineer;
import com.cg.entity.Product;
import com.cg.service.IProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping("/add")
    public String addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return "Product added successfully.";
    }

    @DeleteMapping("/remove/{modelNumber}")
    public String removeProduct(@PathVariable String modelNumber) {
        productService.removeProducts(modelNumber);
        return "Product removed successfully.";
    }

    @GetMapping("/{modelNumber}")
    public List<Product> getProduct(@PathVariable String modelNumber) {
        return productService.getProduct(modelNumber);
    }

    @PutMapping("/update-warranty/{modelNumber}")
    public String updateProductWarranty(@PathVariable String modelNumber) {
        productService.updateProductWarranty(modelNumber);
        return "Product warranty date updated.";
    }

    @GetMapping("/complaints/{modelNumber}")
    public List<Complaint> getProductComplaints(@PathVariable String modelNumber) {
        return productService.getProductComplaints(modelNumber);
    }

    @GetMapping("/engineers/{modelNumber}")
    public List<Engineer> getEngineersByProduct(@PathVariable String modelNumber) {
        return productService.getEngineersByProduct(modelNumber);
    }
}

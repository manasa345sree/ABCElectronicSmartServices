package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByProductName(String productName);

	List<Product> findByModelNumber(String modelNumber);

}


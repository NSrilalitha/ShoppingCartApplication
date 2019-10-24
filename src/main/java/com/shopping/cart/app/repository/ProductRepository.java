package com.shopping.cart.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.cart.app.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	public Product findByProductName(String productName);
	
	public List<Product> findByProductType(String productType);
	
}

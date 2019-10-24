package com.shopping.cart.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shopping.cart.app.model.Product;

@Service
public interface ProductService {

	public List<Product> getAllProducts();
	
	public List<Product> getProductsByType(String productType);
	
	public Product getProductByName(String productName);
	
	public Product getProductById(Long id);
	
}

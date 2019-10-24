package com.shopping.cart.app.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.shopping.cart.app.exception.ProductNotFoundException;
import com.shopping.cart.app.model.Product;
import com.shopping.cart.app.repository.ProductRepository;

/**
 * User should be able to view products. User can search products by type, id or
 * all products available. This service bean provides utility methods to retrieve
 * products list.
 * 
 * @author Srilalitha
 *
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	/*
	 * This method gives all the products
	 */
	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	/*
	 * This method returns products by type.
	 */
	@Override
	public List<Product> getProductsByType(String productType) {
		return productRepository.findByProductType(productType);
	}
	
	/*
	 * This method returns product by name.
	 */
	@Override
	public Product getProductByName(String productName) {
		return productRepository.findByProductName(productName);
	}
	
	/*
	 * This method returns product with specified id.
	 */
	@Override
	public Product getProductById(Long id) {
		try {
			Optional<Product> product = productRepository.findById(id);
			return product.get();
		} catch (NoSuchElementException exception) {
			throw new ProductNotFoundException("Product is not avialable with given id " + id);
		}
	}
	
	
}

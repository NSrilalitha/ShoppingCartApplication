package com.shopping.cart.app.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.cart.app.exception.ProductNotFoundException;
import com.shopping.cart.app.model.Product;
import com.shopping.cart.app.service.ProductService;

/**
 * This class provides rest end points to view products available for booking.
 * User should be able to search products by id, name or category. 
 * 
 * @author Srilalitha
 *
 */
@RestController
@RequestMapping(value="api/v1/products/")
public class ProductController {

	// inject service bean into controller using autowired
	@Autowired
	private ProductService productService;
	
	/**
	 * This method returns all the products avaiable
	 * 
	 * @return ResponseEntity<Collection<Product>>
	 */
	@RequestMapping(value = "getProducts", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Product>> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		if(products.isEmpty()) {
			throw new ProductNotFoundException("Products are not available");
		}
		return new ResponseEntity<Collection<Product>>(products, HttpStatus.OK);
	}
	
	/**
	 * This method returns the products by id given as path variable
	 * 
	 * @param id - product id which is passed as path variable
	 * @return ResponseEntity<Product>
	 */
	@RequestMapping(value = "getProducts/{id}", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> getProductsById(@PathVariable Long id) {
		Product product = productService.getProductById(id);
		if (product == null) {
			throw new ProductNotFoundException("Product not available with given id "+id);
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	/**
	 * This method returns the products by name given as path variable
	 * 
	 * @param name - product name passed as path variable
	 * @return ResponseEntity<Product>
	 */
	@RequestMapping(value = "getProducts/name/{name}", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> getProductsByName(@PathVariable String name) {
		Product product = productService.getProductByName(name);
		if (product == null) {
			throw new ProductNotFoundException("Product not available with given name "+name);
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	/**
	 * This method returns the products by category given as path variable
	 * 
	 * @param category - product category passed as path variable
	 * @return ResponseEntity<Collection<Product>>
	 */
	@RequestMapping(value = "getProducts/type/{category}", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Product>> getProductsByCategory(@PathVariable String category) {
		List<Product> products = productService.getProductsByType(category);
		if(products.isEmpty()) {
			throw new ProductNotFoundException("Products are not available with given type "+category);
		}
		return new ResponseEntity<Collection<Product>>(products, HttpStatus.OK);
	}
	
	
}

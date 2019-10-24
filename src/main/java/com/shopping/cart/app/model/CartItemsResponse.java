package com.shopping.cart.app.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class indicates the response structure while viewing cart items.
 * 
 * @author Srilalitha
 *
 */
public class CartItemsResponse {

	private Double price;
	
	private List<Product> products = new ArrayList<Product>();

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public CartItemsResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartItemsResponse(Double price, List<Product> products) {
		super();
		this.price = price;
		this.products = products;
	}

	@Override
	public String toString() {
		return "CartItemsResponse [price=" + price + ", products=" + products
				+ "]";
	}
	
}

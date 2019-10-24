package com.shopping.cart.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shopping.cart.app.model.Cart;
import com.shopping.cart.app.model.CartItemsResponse;

@Service
public interface CartService {

	public List<Cart> addProductsToCart(Long userId, Long productId);
	
	public CartItemsResponse viewCartItems(Long userId);

	public void deleteProduct(Long id, Long productId);

	public void deleteAllProductsFromCart(Long userId);

	public void updateProductQuantity(Long id, Long productId, int quantity);

	public void validateUser(Long id);

	public void validateProduct(Long productId);
	
}

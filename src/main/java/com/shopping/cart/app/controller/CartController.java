package com.shopping.cart.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.cart.app.exception.UpdateFailureException;
import com.shopping.cart.app.exception.UserNotFoundException;
import com.shopping.cart.app.model.Cart;
import com.shopping.cart.app.model.CartItemsResponse;
import com.shopping.cart.app.service.CartService;
/**
 * This class provides rest end points to view cart, add products to cart, update products in the cart and
 * delete products from cart operations.
 * 
 * @author Srilalitha
 *
 */
@RestController
@RequestMapping(value ="api/v1/cart/")
public class CartController {
	
	@Autowired
	private CartService cartService;

	/**
	 * This method is to display cart items
	 * 
	 * @param id - user id to retrieve cart details
	 * @return - ResponseEntity<List<Product>>
	 */
	@RequestMapping(value="user/{id}/viewCart", method= RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CartItemsResponse> viewCartItems(@PathVariable Long id) {
		// validates user id and throws UserNotFoundException incase user id is
		// invalid
		cartService.validateUser(id);
		CartItemsResponse cartResponse = cartService.viewCartItems(id); 
		return new ResponseEntity<CartItemsResponse>(cartResponse, HttpStatus.OK);
	}
	
	/**
	 * This method provides rest point to add product to the cart.
	 * @param id - user id
	 * @param productId - product id which is going to be added to cart
	 * @return ResponseEntity<List<Cart>> - list of cart objects
	 */
	@RequestMapping(value = "user/{id}/addProduct/{productId}", method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE, 
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cart>> addProductToCart(@PathVariable Long id,
			@PathVariable Long productId) {
		// validates user id and throws UserNotFoundException incase user id is
		// invalid
		cartService.validateUser(id);
		// validates product id and throws ProductNotFoundException incase if
		// product id is invalid
		cartService.validateProduct(productId);
		List<Cart> carts = cartService.addProductsToCart(id, productId);
		return new ResponseEntity<List<Cart>>(carts, HttpStatus.OK);
	}
	
	/**
	 * This method provides rest end point to delete a specific product from
	 * cart.
	 * 
	 * @param id - user id
	 * @param productId - product id
	 * @return
	 */
	@RequestMapping(value = "user/{id}/removeProduct/{productId}", method = RequestMethod.DELETE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> removeProductFromCart(@PathVariable Long id, @PathVariable Long productId) {
		// validates user id and throws UserNotFoundException incase user id is
		// invalid
		cartService.validateUser(id);
		// validates product id and throws ProductNotFoundException incase if
		// product id is invalid
		cartService.validateProduct(productId);
		try {
			cartService.deleteProduct(id,productId);
		} catch(Exception e) {
			throw new UserNotFoundException("resource not found"+e);
		}
		return ResponseEntity.ok().build();
	}
	
	/**
	 * This method provides rest end point to delete all products from
	 * cart.
	 * 
	 * @param id - user id
	 * @return
	 */
	@RequestMapping(value = "user/{id}/removeAll", method = RequestMethod.DELETE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> removeAllProductsFromCart(@PathVariable Long id) {
		// validates user id and throws UserNotFoundException in case user id is
		// invalid
		cartService.validateUser(id);
		try {
			cartService.deleteAllProductsFromCart(id);
		} catch(Exception e) {
			throw new UserNotFoundException("resource not found"+e);
		}
		return ResponseEntity.ok().build();
	}
	
	/**
	 * This method provides rest end point to perform update operation i.e., update
	 * product quantity in cart for the specific user.
	 * 
	 * @param id - user id
	 * @param productId - product id
	 * @param quantity - quantity of the product.
	 * @return
	 */
	@RequestMapping(value = "user/{id}/updateProduct/{productId}/quantity/{quantity}", method = RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateProductQuantity(@PathVariable Long id,
			@PathVariable Long productId, @PathVariable int quantity) {
		// validates user id and throws UserNotFoundException incase user id is
		// invalid
		cartService.validateUser(id);
		// validates product id and throws ProductNotFoundException incase if
		// product id is invalid
		cartService.validateProduct(productId);
		if (quantity < 0) {
			throw new UpdateFailureException("Quantity should not be negative");
		}
		cartService.updateProductQuantity(id, productId, quantity);
		return ResponseEntity.ok().build();
	}
	
}

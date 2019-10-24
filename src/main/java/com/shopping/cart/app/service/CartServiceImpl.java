package com.shopping.cart.app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.cart.app.exception.ProductNotFoundException;
import com.shopping.cart.app.exception.UserNotFoundException;
import com.shopping.cart.app.model.Cart;
import com.shopping.cart.app.model.CartItemsResponse;
import com.shopping.cart.app.model.Product;
import com.shopping.cart.app.model.User;
import com.shopping.cart.app.repository.CartRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class CartServiceImpl implements CartService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CartRepository cartRepository;
	
	/**
	 * This method is to add product to the cart.
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public List<Cart> addProductsToCart(Long userId, Long productId) {
		
		User user = userService.getUserDetails(userId);
		logger.info("user object : "+user);
		
		// Get cart details from Cart table using userId
		List<Cart> carts = cartRepository.findByUserId(userId);
		logger.info("cart objects retrieved by user id is.. "+carts.toString());
		
		if (carts.isEmpty()) {
			// cart is empty. Add product to cart.
			cartRepository.saveProductToCart(productId, 1, user);			
		} else {
			// cart is not empty 
			boolean isUpdateRequired = false;
			// iterate through cart objects which has mentioned user id
			for (Cart cart: carts) {
				// if same product available in the cart, increment its quantity by one.
				if(cart.getProductId() == productId) {
					logger.info("updating same product====");
					isUpdateRequired = true;
					int quantity = cart.getQuantity()+1;
					// update cart corresponding productId
					cartRepository.updateProductInCart(quantity, productId, user);
					break;
				} 
			}
			// if product is not added to cart yet, then add that product to cart.
			if (!isUpdateRequired) {
				logger.info("adding one more product to cart");
				// adding new product to cart
				cartRepository.saveProductToCart(productId, 1, user);
			}
		}
		// retrieve cart 
		carts = cartRepository.findByUserId(userId);
		logger.info("after adding products, cart object is "+carts);
		
		return carts;
	}

	/**
	 * This method returns cart response to display cart items.
	 */
	@Override
	public CartItemsResponse viewCartItems(Long userId) {
		List<Cart> cartItems = cartRepository.findByUserId(userId);
		// prepare cart response
		CartItemsResponse response = new CartItemsResponse();
		// iterate through cart items and find total price and prepare cart response
		Double price = 0.0;
		for(Cart cartItem: cartItems) {
			Product product = productService.getProductById(cartItem.getProductId());
			Double productPrice = product.getPrice();
			int quantity = cartItem.getQuantity();
			price = price + (productPrice*quantity);
			// add products based on quantity
			for (int i=0;i<quantity;i++) {
				response.getProducts().add(product);
			}	
		}
		response.setPrice(price);
		return response;
	}

	/**
	 * This method is to delete product from cart.
	 */
	@Override
	public void deleteProduct(Long id, Long productId) {
		// TODO Auto-generated method stub
		User user = userService.getUserDetails(id);
		// remove cart that matches with user id and product id
		cartRepository.deleteCartByUserIdProductId(user, productId);
	}

	/**
	 * This method is to remove all products from cart for the mentioned user.
	 */
	@Override
	public void deleteAllProductsFromCart(Long userId) {
		// TODO Auto-generated method stub
		User user = userService.getUserDetails(userId);
		// remove cart that matches with user id
		cartRepository.deleteCartByUserId(user);
	}

	/**
	 * This method updates quantity of a product for the specific user.
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void updateProductQuantity(Long id, Long productId, int quantity) {
		// TODO Auto-generated method stub
		User user = userService.getUserDetails(id);
		if (quantity == 0) {
			// remove product from cart
			deleteProduct(id, productId);
		}
		// retrieve cart details by user id
		List<Cart> carts = cartRepository.findByUserId(id);
		boolean isProductExists = false;
		for(Cart cart: carts) {
			// check given product id is availble in cart or not
			if(cart.getProductId() == productId) {
				isProductExists = true;
				break;
			}
		}
		if (!isProductExists) {
			throw new ProductNotFoundException("Cannot update product since product id is not available in the cart");
		}
		// update quantity
		cartRepository.updateProductInCart(quantity, productId, user);
	}

	/**
	 * Checks user exists with mentioned user id. If user doesn't exist with
	 * mentioned id, then it throws UserNotFoundException.
	 */
	@Override
	public void validateUser(Long id) {
		User user = userService.getUserDetails(id);
		if (user == null) {
			throw new UserNotFoundException("User doesn't exist");
		} 
	}

	@Override
	public void validateProduct(Long productId) {
		Product product = productService.getProductById(productId);
		if(product == null) {
			throw new ProductNotFoundException("Product is not available with id "+productId);
		}
	}
	
}

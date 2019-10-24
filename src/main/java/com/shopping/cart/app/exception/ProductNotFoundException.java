package com.shopping.cart.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This is a custom exception class. This custom exception is thrown when product
 * is not available.
 * 
 * @author Srilalitha
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8288359372789520372L;

	public ProductNotFoundException(String exception) {
        super(exception);
    }
	
}

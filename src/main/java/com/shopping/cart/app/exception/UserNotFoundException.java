package com.shopping.cart.app.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3918668729541079302L;

	public UserNotFoundException(String exception) {
		super(exception);
	}
}

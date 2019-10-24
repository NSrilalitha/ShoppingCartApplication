package com.shopping.cart.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.cart.app.exception.UserNotFoundException;
import com.shopping.cart.app.model.User;
import com.shopping.cart.app.service.UserService;

/**
 * This class has only one rest end point to check whether user logged in or not.
 * @author Srilalitha
 *
 */
@RestController
@RequestMapping(value="api/v1/users/")
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 * This method returns the User by id given as path variable
	 * 
	 * @param id - user id which is passed as path variable
	 * @return ResponseEntity<User>
	 */
	@RequestMapping(value = "getUsers/{id}", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		User user = userService.getUserDetails(id);
		if (user == null) {
			throw new UserNotFoundException("User doesn't exist");
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
}

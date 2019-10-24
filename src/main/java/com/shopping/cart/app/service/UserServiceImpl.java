package com.shopping.cart.app.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.cart.app.exception.UserNotFoundException;
import com.shopping.cart.app.model.User;
import com.shopping.cart.app.repository.UserRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	/**
	 * This method is check whether specific user present or not
	 */
	@Override
	public User getUserDetails(Long id) {
		// Fetching user details from db
		try {
			Optional<User> user = userRepository.findById(id);
			return user.get();
		} catch(NoSuchElementException e) {
			throw new UserNotFoundException("User doesn't exist..");
		}
	}
	
}

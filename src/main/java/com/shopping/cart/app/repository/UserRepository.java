package com.shopping.cart.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.cart.app.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}

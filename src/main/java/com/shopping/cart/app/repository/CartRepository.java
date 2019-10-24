package com.shopping.cart.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.cart.app.model.Cart;
import com.shopping.cart.app.model.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

	@Modifying
    @Query(value = "insert into Cart (product_id,quantity,user_id) VALUES (:productId,:quantity,:user)", nativeQuery = true)
    @Transactional
    void saveProductToCart(@Param("productId") Long productId, @Param("quantity") int quantity, @Param("user") User user);

	@Modifying
	@Query(value = "update Cart u set u.quantity = :quantity where u.productId = :productId and u.user = :user")
	@Transactional
	void updateProductInCart(@Param("quantity") int quantity, @Param("productId") Long productId, @Param("user") User user);
	
	@Modifying
	@Query("delete from Cart u WHERE u.user = :user and u.productId = :productId")
	@Transactional
	void deleteCartByUserIdProductId(@Param("user") User user, @Param("productId") Long productId);

	@Modifying
	@Query("delete from Cart u WHERE u.user = :user")
	@Transactional
	void deleteCartByUserId(@Param("user") User user);
	
	public List<Cart> findByUserId(Long userId);
	
}

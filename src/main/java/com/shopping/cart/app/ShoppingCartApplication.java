package com.shopping.cart.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.shopping.cart.app.model.Apparel;
import com.shopping.cart.app.model.Book;
import com.shopping.cart.app.model.Product;
import com.shopping.cart.app.model.User;
import com.shopping.cart.app.repository.ProductRepository;
import com.shopping.cart.app.repository.UserRepository;

/**
 * This is main class
 *
 */
@SpringBootApplication
@EnableTransactionManagement
public class ShoppingCartApplication implements CommandLineRunner
{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// inject ProductRepository so that we can save few products into database.
	@Autowired
	private ProductRepository productRepository;
	
	// inject UserRepository so that we can save few users into database.
	@Autowired
	private UserRepository userRepository;
	
    public static void main( String[] args )
    {
        SpringApplication.run(ShoppingCartApplication.class,args);
    }

    // The content inside run method gets executed just before
    // spring finishes its startup.
	@Override
	public void run(String... args) throws Exception {
		// Inserting data into product table.
		logger.info("inserting data into products table");
		// Add product 1
		Product product1 = new Book("music", "Ranbir", "KS Publications");
		product1.setProductId(1L);
		product1.setProductName("Interesting tales");
		product1.setPrice(100.0);
		
		// Add product 2
		Product product2 = new Apparel("HRX","checks");
		product2.setProductId(2L);
		product2.setProductName("Blue color shirt");
		product2.setPrice(500.0);
		
		// Add product 3
		Product product3 = new Apparel("HRX","Casual");
		product3.setProductId(3L);
		product3.setProductName("Grey casual shirt");
		product3.setPrice(750.0);
		
		// Add product 4
		Product product4 = new Book("Drama", "Sanjana", "Aarthi Publications");
		product4.setProductId(4L);
		product4.setProductName("Bangalore days");
		product4.setPrice(250.0);
		
		// Add product 5
		Product product5 = new Apparel("VanHusen","Formal");
		product5.setProductId(5L);
		product5.setProductName("White formal shirt");
		product5.setPrice(800.0);
		
		
		productRepository.save(product1);
		productRepository.save(product2);
		productRepository.save(product3);
		productRepository.save(product4);
		productRepository.save(product5);
		
		logger.info("inserting data into users table");
		// inserting data into users table.
		User user1 = new User(1L, "Ram");
		User user2 = new User(2L, "Rahul");
		User user3 = new User(3L, "Archana");
				
		// save user objects into database
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
				
	}
}

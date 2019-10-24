# ShoppingCartApplication
Shopping cart application which manages cart operations

Application
----------
We have list of products with different categories. Each category is a sub class of product class. User can seach products by its id/name/categor.
User should not modify product details. User can perform following operations:

1. User should be able to view products
2. User should be able to search product by id,name and category.
3. User can add product to cart
4. User can update product quantity already added into the cart.
5. User can delete product from cart.
6. User can delete all the products from cart.

Expose REST APIs to perform these operations.

Entity classes : User, Cart, Product and its sub classes based on categories.

Application requirements: Spring-boot, mysql database, knowledge on Spring boot JPA, hibernate to connect with database and perform db operations.

Tools: STS, Postman, mysql workbench

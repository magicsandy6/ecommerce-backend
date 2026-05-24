# E-Commerce Backend API

A REST API for an e-commerce application built with Spring Boot, featuring JWT authentication, product management, shopping cart, orders, and product reviews.

## Features

- **User Management**: Registration, login with JWT authentication
- **Product Management**: CRUD operations for products
- **Shopping Cart**: Add/remove items, manage quantities
- **Orders**: Create and track orders
- **Product Reviews**: Rate and review products with average ratings
- **Security**: Spring Security with JWT tokens
- **Database**: MySQL with Hibernate ORM

## Tech Stack

- Java 17
- Spring Boot 4.0.6
- Spring Security
- JWT (JSON Web Tokens)
- Spring Data JPA
- Hibernate
- MySQL
- Maven

## API Endpoints

### Authentication
- `POST /api/auth/login` - Login user
- `POST /api/users/register` - Register new user

### Products
- `GET /api/products` - Get all products
- `GET /api/products/{id}` - Get product by ID
- `POST /api/products` - Add new product
- `PUT /api/products/{id}` - Update product
- `DELETE /api/products/{id}` - Delete product

### Cart
- `POST /api/cart/add` - Add item to cart
- `GET /api/cart/user/{userId}` - Get user's cart
- `DELETE /api/cart/{cartId}` - Remove item from cart
- `PUT /api/cart/{cartId}/quantity/{quantity}` - Update quantity

### Orders
- `POST /api/orders/user/{userId}` - Create order
- `GET /api/orders/{id}` - Get order by ID
- `GET /api/orders/user/{userId}` - Get user's orders
- `PUT /api/orders/{id}/status/{status}` - Update order status

### Reviews
- `POST /api/reviews/product/{productId}/user/{userId}` - Add review
- `GET /api/reviews/product/{productId}` - Get product reviews
- `GET /api/reviews/product/{productId}/average-rating` - Get average rating

## Setup

1. Clone the repository
2. Configure MySQL database in `application.properties`
3. Run `mvn clean install`
4. Run the application: `mvn spring-boot:run`

The API will be available at `http://localhost:8080`

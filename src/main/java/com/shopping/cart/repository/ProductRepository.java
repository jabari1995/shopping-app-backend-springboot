package com.shopping.cart.repository;

import com.shopping.cart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The ProductRepository interface provides methods to interact with the product table in the database.
 * It extends the JpaRepository interface, which provides the basic CRUD operations for entities.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
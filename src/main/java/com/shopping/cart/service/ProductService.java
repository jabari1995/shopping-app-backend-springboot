package com.shopping.cart.service;

import com.shopping.cart.model.Product;
import com.shopping.cart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The ProductService class provides methods to retrieve product data.
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    /**
     * Retrieves all products.
     *
     * @return a list of Product objects representing all the products
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
package com.shopping.cart.controller;

import com.shopping.cart.model.Product;
import com.shopping.cart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The ProductController class is a REST controller that handles HTTP requests related to products.
 * It provides methods for retrieving all products.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * Retrieves all products.
     *
     * @return a list of Product objects representing all the products
     */
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
}

package com.shopping.cart.controller;

import com.shopping.cart.model.Product;
import com.shopping.cart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The ProductController class is a REST controller that handles HTTP requests related to products.
 * It provides methods for retrieving all products.
 */
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
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

    /**
     * Adds a new product.
     *
     * @param product The product to add.
     * @return The added product.
     */
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    /**
     * Updates an existing product.
     *
     * @param product The product with updated information.
     * @return The updated product.
     */
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        product.setId(id);
        return productService.updateProduct(product);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to be deleted
     * @return a message indicating whether the product was deleted
     */
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        boolean isRemoved = productService.deleteProduct(id);
        if (!isRemoved) {
            return "Error: Product not found or could not be deleted.";
        }
        return "Product deleted successfully.";
    }
}

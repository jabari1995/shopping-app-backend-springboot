package com.shopping.cart.service;

import com.shopping.cart.model.Product;
import com.shopping.cart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    /**
     * Saves a new product.
     *
     * @param product The product to save.
     * @return The saved product.
     */
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Updates an existing product.
     *
     * @param product The product with updated information.
     * @return The updated product.
     */
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to be deleted
     * @return true if the product was deleted, false otherwise
     */
    public boolean deleteProduct(int id){
        Optional<Product> product = productRepository.findById((long) id);
        if (product.isPresent()) {
            productRepository.delete(product.get());
            return true;
        }
        return false;
    }
}
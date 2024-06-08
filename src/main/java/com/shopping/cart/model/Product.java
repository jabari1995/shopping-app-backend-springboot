package com.shopping.cart.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * The Product class represents a product with its properties such as id, name, price, and imgUrl.
 * It is an entity class that is mapped to the "product" table in the database.
 */
@Setter
@Getter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private String imgUrl;

    /**
     * Default constructor for the Product class.
     * Creates a new instance of the Product class.
     */
    public Product() {}

    /**
     * Initializes a new instance of the Product class with the given parameters.
     *
     * @param id The ID of the product.
     * @param name The name of the product.
     * @param price The price of the product.
     * @param imgUrl The URL of the image of the product.
     */
    public Product(int id, String name, double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
    }

}
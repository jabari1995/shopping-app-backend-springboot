package com.shopping.cart.controller;

import com.shopping.cart.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/products")
    public List<Product> getProducts() {
        return Arrays.asList(
                new Product(1, "Lockere Crinkle-Bluse mit Knopfleiste", 22.99, "/imgs/crinkleBluse.jpg"),
                new Product(2, "Baby Overall Bio-Baumwolle", 16.99, "/imgs/baby.jpg"),
                new Product(3, "Poloshirt, Kurzarm (2er Pack)", 25.99, "/imgs/polo.jpg"),
                new Product(4, "Gartenmöbel Set (7-tlg.Set)", 328.99, "/imgs/gartenmoebel.jpg")
        );
    }
}

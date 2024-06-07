package com.shopping.cart.controller;

import com.shopping.cart.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductController productController;

    @Test
    public void testGetProducts() throws Exception {
        // Arrange
        List<Product> products = Arrays.asList(
                new Product(1, "Lockere Crinkle-Bluse mit Knopfleiste", 22.99, "/imgs/crinkleBluse.jpg"),
                new Product(2, "Baby Overall Bio-Baumwolle", 16.99, "/imgs/baby.jpg"),
                new Product(3, "Poloshirt, Kurzarm (2er Pack)", 25.99, "/imgs/polo.jpg"),
                new Product(4, "Gartenmöbel Set (7-tlg.Set)", 328.99, "/imgs/gartenmoebel.jpg")
        );
        given(productController.getProducts()).willReturn(products);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(products.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Lockere Crinkle-Bluse mit Knopfleiste"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Baby Overall Bio-Baumwolle"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name").value("Poloshirt, Kurzarm (2er Pack)"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].name").value("Gartenmöbel Set (7-tlg.Set)"));
    }
}
package com.shopping.cart.controller;

import com.shopping.cart.model.Product;
import com.shopping.cart.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testGetProducts() throws Exception {
        // Arrange
        List<Product> products = Arrays.asList(
                new Product(1, "Lockere Crinkle-Bluse mit Knopfleiste", 22.99, "/imgs/crinkleBluse.jpg"),
                new Product(2, "Baby Overall Bio-Baumwolle", 16.99, "/imgs/baby.jpg"),
                new Product(3, "Poloshirt, Kurzarm (2er Pack)", 25.99, "/imgs/polo.jpg"),
                new Product(4, "Gartenmöbel Set (7-tlg.Set)", 328.99, "/imgs/gartenmoebel.jpg")
        );

        // Mocking service response
        when(productService.getAllProducts()).thenReturn(products);

        // Act & Assert
        mockMvc.perform(get("/api/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.length()").value(products.size()))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Lockere Crinkle-Bluse mit Knopfleiste"))
                .andExpect(jsonPath("$[0].price").value(22.99))
                .andExpect(jsonPath("$[0].imgUrl").value("/imgs/crinkleBluse.jpg"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Baby Overall Bio-Baumwolle"))
                .andExpect(jsonPath("$[1].price").value(16.99))
                .andExpect(jsonPath("$[1].imgUrl").value("/imgs/baby.jpg"))
                .andExpect(jsonPath("$[2].id").value(3))
                .andExpect(jsonPath("$[2].name").value("Poloshirt, Kurzarm (2er Pack)"))
                .andExpect(jsonPath("$[2].price").value(25.99))
                .andExpect(jsonPath("$[2].imgUrl").value("/imgs/polo.jpg"))
                .andExpect(jsonPath("$[3].id").value(4))
                .andExpect(jsonPath("$[3].name").value("Gartenmöbel Set (7-tlg.Set)"))
                .andExpect(jsonPath("$[3].price").value(328.99))
                .andExpect(jsonPath("$[3].imgUrl").value("/imgs/gartenmoebel.jpg"));
    }
}

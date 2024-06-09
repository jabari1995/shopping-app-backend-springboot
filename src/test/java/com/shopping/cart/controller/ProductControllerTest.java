package com.shopping.cart.controller;

import com.shopping.cart.model.Product;
import com.shopping.cart.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
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

    @Test
    public void testAddProduct() throws Exception {
        Product product = new Product(1, "New Product", 99.99, "/imgs/newProduct.jpg");

        when(productService.saveProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(product)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("New Product"))
                .andExpect(jsonPath("$.price").value(99.99))
                .andExpect(jsonPath("$.imgUrl").value("/imgs/newProduct.jpg"));

        verify(productService, times(1)).saveProduct(any(Product.class));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Product updatedProduct = new Product(1, "Updated Product", 199.99, "/imgs/updatedProduct.jpg");

        when(productService.updateProduct(any(Product.class))).thenReturn(updatedProduct);

        mockMvc.perform(put("/api/products/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedProduct)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Product"))
                .andExpect(jsonPath("$.price").value(199.99))
                .andExpect(jsonPath("$.imgUrl").value("/imgs/updatedProduct.jpg"));

        verify(productService, times(1)).updateProduct(any(Product.class));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        when(productService.deleteProduct(1)).thenReturn(true);

        mockMvc.perform(delete("/api/products/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Product deleted successfully."));

        verify(productService, times(1)).deleteProduct(1);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

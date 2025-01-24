package com.example.SpringBootPractice002.controllers;

import com.example.SpringBootPractice002.models.Product;
import com.example.SpringBootPractice002.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    public void testGetProduct() throws Exception {
        Product product = new Product(1L, "Test Product-10001", 100.0);
        Mockito.when(productService.getProductById(1L)).thenReturn(product);
        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Test Product-10001")))
                .andExpect(jsonPath("$.price", is(100.0)));
    }

    public void testCreateProduct() throws Exception {
        Product product = new Product("Test Product", 200.0);
        Product savedProduct = new Product(1L, "Test Product", 200.0);

        Mockito.when(productService.saveProduct(Mockito.any(Product.class))).thenReturn(savedProduct);

        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Test Product\",\"price\":200.0}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Test Product")))
                .andExpect(jsonPath("$.price", is(200.0)));

    }
}

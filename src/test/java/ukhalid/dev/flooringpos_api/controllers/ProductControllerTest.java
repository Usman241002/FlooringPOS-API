package ukhalid.dev.flooringpos_api.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ukhalid.dev.flooringpos_api.entities.Brand;
import ukhalid.dev.flooringpos_api.entities.Category;
import ukhalid.dev.flooringpos_api.entities.Product;
import ukhalid.dev.flooringpos_api.services.ProductService;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setUp() {
        Brand brand = new Brand();
        brand.setName("Test Brand");

        Category category = new Category();
        category.setName("Test Category");

        product = new Product();
        product.setBrand(brand);
        product.setCategory(category);
        product.setName("Test Product");
        product.setSellingPriceM2(new BigDecimal("10.00"));
        product.setCostPriceM2(new BigDecimal("6.90"));
        product.setIsActive(true);
    }

    // GET /products

    @Test
    void getAllProducts_shouldReturnProducts() throws Exception {
        when(productService.getAllProducts())
                .thenReturn(List.of(product));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(
                        MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name")
                        .value("Test Product"));

        verify(productService).getAllProducts();
    }

    @Test
    void getAllProducts_shouldReturnEmptyArray_whenNoProductsExist()
            throws Exception {
        when(productService.getAllProducts())
                .thenReturn(List.of());

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }

    // GET /products/{id}

    @Test
    void getProductById_shouldReturnProduct() throws Exception {
        when(productService.getProductById(1))
                .thenReturn(product);

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name")
                        .value("Test Product"))
                .andExpect(jsonPath("$.sellingPriceM2")
                        .value(10.00));

        verify(productService).getProductById(1);
    }

    // POST /products

    @Test
    void createProduct_shouldReturnCreatedProduct() throws Exception {
        when(productService.createProduct(any()))
                .thenReturn(product);

        String requestBody = """
                {
                    "brand": 1,
                    "category": 1,
                    "name": "Test Product",
                    "sellingPriceM2": 10.00,
                    "costPriceM2": 6.90,
                    "isActive": true
                }
                """;

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name")
                        .value("Test Product"))
                .andExpect(jsonPath("$.sellingPriceM2")
                        .value(10.00))
                .andExpect(jsonPath("$.costPriceM2")
                        .value(6.90))
                .andExpect(jsonPath("$.isActive")
                        .value(true));

        verify(productService).createProduct(any());
    }

    // PUT /products/{id}

    @Test
    void updateProduct_shouldReturnUpdatedProduct() throws Exception {
        product.setName("Updated Product");
        product.setSellingPriceM2(new BigDecimal("12.50"));

        when(productService.updateProduct(eq(1), any()))
                .thenReturn(product);

        String requestBody = """
                {
                    "brand": 1,
                    "category": 1,
                    "name": "Updated Product",
                    "sellingPriceM2": 12.50,
                    "costPriceM2": 6.90,
                    "isActive": true
                }
                """;

        mockMvc.perform(put("/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name")
                        .value("Updated Product"))
                .andExpect(jsonPath("$.sellingPriceM2")
                        .value(12.50));

        verify(productService).updateProduct(eq(1), any());
    }

    // DELETE /products/{id}

    @Test
    void deleteProduct_shouldDeleteProduct() throws Exception {
        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isOk());

        verify(productService).deleteProduct(1);
    }
}

package ukhalid.dev.flooringpos_api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ukhalid.dev.flooringpos_api.entities.Product;
import ukhalid.dev.flooringpos_api.services.ProductService;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Test
    void shouldGetAllProducts() throws Exception {
        Product product = createProduct(
                "Oak Flooring",
                "32.50",
                "20",
                "2.40"
        );

        when(productService.getAllProducts())
                .thenReturn(List.of(product));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Oak Flooring"))
                .andExpect(jsonPath("$[0].pricePerM2").value(32.50))
                .andExpect(jsonPath("$[0].stockQuantityUnits").value(20))
                .andExpect(jsonPath("$[0].m2PerUnit").value(2.40));

        verify(productService).getAllProducts();
    }

    @Test
    void shouldGetProductById() throws Exception {
        Product product = createProduct(
                "Oak Flooring",
                "32.50",
                "20",
                "2.40"
        );

        when(productService.getProductById(1))
                .thenReturn(product);

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Oak Flooring"))
                .andExpect(jsonPath("$.pricePerM2").value(32.50))
                .andExpect(jsonPath("$.stockQuantityUnits").value(20))
                .andExpect(jsonPath("$.m2PerUnit").value(2.40));

        verify(productService).getProductById(1);
    }

    @Test
    void shouldCreateProduct() throws Exception {
        Product product = createProduct(
                "Oak Flooring",
                "32.50",
                "20",
                "2.40"
        );

        when(productService.createProduct(any(Product.class)))
                .thenReturn(product);

        mockMvc.perform(
                        post("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(product))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Oak Flooring"))
                .andExpect(jsonPath("$.pricePerM2").value(32.50))
                .andExpect(jsonPath("$.stockQuantityUnits").value(20))
                .andExpect(jsonPath("$.m2PerUnit").value(2.40));

        verify(productService).createProduct(any(Product.class));
    }

    @Test
    void shouldUpdateProduct() throws Exception {
        Product product = createProduct(
                "Walnut Flooring",
                "45.00",
                "10",
                "2.40"
        );

        when(productService.updateProduct(eq(1), any(Product.class)))
                .thenReturn(product);

        mockMvc.perform(
                        put("/products/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(product))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Walnut Flooring"))
                .andExpect(jsonPath("$.pricePerM2").value(45.00))
                .andExpect(jsonPath("$.stockQuantityUnits").value(10))
                .andExpect(jsonPath("$.m2PerUnit").value(2.40));

        verify(productService)
                .updateProduct(eq(1), any(Product.class));
    }

    @Test
    void shouldDeleteProduct() throws Exception {
        Product product = createProduct(
                "Oak Flooring",
                "32.50",
                "20",
                "2.40"
        );

        when(productService.deleteProduct(1))
                .thenReturn(product);

        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Oak Flooring"));

        verify(productService).deleteProduct(1);
    }

    private Product createProduct(
            String name,
            String pricePerM2,
            String stockQuantityUnits,
            String m2PerUnit
    ) {
        Product product = new Product();

        product.setName(name);
        product.setPricePerM2(new BigDecimal(pricePerM2));
        product.setStockQuantityUnits(new BigDecimal(stockQuantityUnits));
        product.setM2PerUnit(new BigDecimal(m2PerUnit));

        return product;
    }
}


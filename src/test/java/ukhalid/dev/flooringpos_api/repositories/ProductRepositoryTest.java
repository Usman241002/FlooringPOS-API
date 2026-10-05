package ukhalid.dev.flooringpos_api.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import ukhalid.dev.flooringpos_api.entities.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldFindAllProducts() {
        Product product1 = new Product();
        product1.setName("Oak Flooring");
        product1.setPricePerM2(new BigDecimal("32.50"));
        product1.setStockQuantityUnits(BigDecimal.valueOf(100));

        Product product2 = new Product();
        product2.setName("Walnut Flooring");
        product2.setPricePerM2(new BigDecimal("45.00"));
        product2.setStockQuantityUnits(BigDecimal.valueOf(50.00));

        productRepository.save(product1);
        productRepository.save(product2);

        List<Product> products = productRepository.findAll();

        assertEquals(2, products.size());
    }

    @Test
    void shouldFindProductById() {
        Product product = new Product();
        product.setName("Oak Flooring");
        product.setPricePerM2(new BigDecimal("32.50"));
        product.setStockQuantityUnits(BigDecimal.valueOf(100));

        Product savedProduct = productRepository.save(product);

        Optional<Product> foundProduct =
                productRepository.findById(savedProduct.getId());

        assertTrue(foundProduct.isPresent());
        assertEquals("Oak Flooring", foundProduct.get().getName());
    }

    @Test
    void shouldSaveProduct() {
        Product product = new Product();
        product.setName("Oak Flooring");
        product.setPricePerM2(new BigDecimal("32.50"));
        product.setStockQuantityUnits(BigDecimal.valueOf(100));

        Product savedProduct = productRepository.save(product);

        assertNotNull(savedProduct.getId());
        assertEquals("Oak Flooring", savedProduct.getName());
        assertEquals(new BigDecimal("32.50"), savedProduct.getPricePerM2());
        assertEquals(100, savedProduct.getStockQuantityUnits());
    }

    @Test
    void shouldDeleteProduct() {
        Product product = new Product();
        product.setName("Oak Flooring");
        product.setPricePerM2(new BigDecimal("32.50"));
        product.setStockQuantityUnits(BigDecimal.valueOf(100));

        Product savedProduct = productRepository.save(product);

        productRepository.delete(savedProduct);

        Optional<Product> deletedProduct =
                productRepository.findById(savedProduct.getId());

        assertTrue(deletedProduct.isEmpty());
    }
}

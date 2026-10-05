package ukhalid.dev.flooringpos_api.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ukhalid.dev.flooringpos_api.entities.Product;
import ukhalid.dev.flooringpos_api.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldGetAllProducts() {
        Product product1 = createProduct("Oak Flooring", "32.50", "20", "2.40");
        Product product2 = createProduct("Walnut Flooring", "45.00", "10", "2.40");

        when(productRepository.findAll())
                .thenReturn(List.of(product1, product2));

        List<Product> products = productService.getAllProducts();

        assertEquals(2, products.size());
        assertEquals("Oak Flooring", products.get(0).getName());
        assertEquals("Walnut Flooring", products.get(1).getName());

        verify(productRepository).findAll();
    }

    @Test
    void shouldGetProductById() {
        Product product = createProduct("Oak Flooring", "32.50", "20", "2.40");

        when(productRepository.findById(1))
                .thenReturn(Optional.of(product));

        Product result = productService.getProductById(1);

        assertNotNull(result);
        assertEquals("Oak Flooring", result.getName());
        assertEquals(new BigDecimal("32.50"), result.getPricePerM2());
        assertEquals(new BigDecimal("20"), result.getStockQuantityUnits());
        assertEquals(new BigDecimal("2.40"), result.getM2PerUnit());

        verify(productRepository).findById(1);
    }

    @Test
    void shouldReturnNullWhenProductDoesNotExist() {
        when(productRepository.findById(1))
                .thenReturn(Optional.empty());

        Product result = productService.getProductById(1);

        assertNull(result);

        verify(productRepository).findById(1);
    }

    @Test
    void shouldCreateProduct() {
        Product product = createProduct("Oak Flooring", "32.50", "20", "2.40");

        when(productRepository.save(product))
                .thenReturn(product);

        Product result = productService.createProduct(product);

        assertEquals(product, result);

        verify(productRepository).save(product);
    }

    @Test
    void shouldUpdateProduct() {
        Product existingProduct =
                createProduct("Oak Flooring", "32.50", "20", "2.40");

        Product updatedProduct =
                createProduct("Walnut Flooring", "45.00", "10", "2.40");

        when(productRepository.findById(1))
                .thenReturn(Optional.of(existingProduct));

        when(productRepository.save(existingProduct))
                .thenReturn(existingProduct);

        Product result = productService.updateProduct(1, updatedProduct);

        assertEquals("Walnut Flooring", result.getName());
        assertEquals(new BigDecimal("45.00"), result.getPricePerM2());
        assertEquals(new BigDecimal("10"), result.getStockQuantityUnits());
        assertEquals(new BigDecimal("2.40"), result.getM2PerUnit());

        verify(productRepository).findById(1);
        verify(productRepository).save(existingProduct);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistentProduct() {
        Product product =
                createProduct("Oak Flooring", "32.50", "20", "2.40");

        when(productRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(
                IllegalArgumentException.class,
                () -> productService.updateProduct(1, product)
        );

        verify(productRepository).findById(1);
        verify(productRepository, never()).save(any());
    }

    @Test
    void shouldDeleteProduct() {
        Product product =
                createProduct("Oak Flooring", "32.50", "20", "2.40");

        when(productRepository.findById(1))
                .thenReturn(Optional.of(product));

        Product result = productService.deleteProduct(1);

        assertEquals(product, result);

        verify(productRepository).findById(1);
        verify(productRepository).delete(product);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistentProduct() {
        when(productRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(
                IllegalArgumentException.class,
                () -> productService.deleteProduct(1)
        );

        verify(productRepository).findById(1);
        verify(productRepository, never()).delete(any());
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


package ukhalid.dev.flooringpos_api.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ukhalid.dev.flooringpos_api.dtos.ProductDTO;
import ukhalid.dev.flooringpos_api.entities.Brand;
import ukhalid.dev.flooringpos_api.entities.Category;
import ukhalid.dev.flooringpos_api.entities.Product;
import ukhalid.dev.flooringpos_api.repositories.BrandRepository;
import ukhalid.dev.flooringpos_api.repositories.CategoryRepository;
import ukhalid.dev.flooringpos_api.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductService productService;

    private Brand brand;
    private Category category;
    private Product product;
    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        brand = new Brand();
        brand.setName("Test Brand");

        category = new Category();
        category.setName("Test Category");

        product = new Product();
        product.setBrand(brand);
        product.setCategory(category);
        product.setName("Test Product");
        product.setSellingPriceM2(new BigDecimal("10.00"));
        product.setCostPriceM2(new BigDecimal("6.90"));
        product.setIsActive(true);

        productDTO = new ProductDTO();
        productDTO.setBrand(1);
        productDTO.setCategory(1);
        productDTO.setName("Test Product");
        productDTO.setSellingPriceM2(new BigDecimal("10.00"));
        productDTO.setCostPriceM2(new BigDecimal("6.90"));
        productDTO.setIsActive(true);
    }

    @Test
    void getAllProducts_shouldReturnAllProducts() {
        List<Product> products = List.of(product);

        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertThat(result).hasSize(1);
        assertThat(result).containsExactly(product);

        verify(productRepository).findAll();
    }

    @Test
    void getProductById_shouldReturnProduct_whenProductExists() {
        when(productRepository.findById(1))
                .thenReturn(Optional.of(product));

        Product result = productService.getProductById(1);

        assertThat(result).isSameAs(product);

        verify(productRepository).findById(1);
    }

    @Test
    void getProductById_shouldReturnNull_whenProductDoesNotExist() {
        when(productRepository.findById(99))
                .thenReturn(Optional.empty());

        Product result = productService.getProductById(99);

        assertThat(result).isNull();

        verify(productRepository).findById(99);
    }

    @Test
    void createProduct_shouldSaveProduct_whenBrandAndCategoryExist() {
        when(brandRepository.findById(1))
                .thenReturn(Optional.of(brand));

        when(categoryRepository.findById(1))
                .thenReturn(Optional.of(category));

        when(productRepository.save(any(Product.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Product result = productService.createProduct(productDTO);

        assertThat(result.getName()).isEqualTo("Test Product");
        assertThat(result.getBrand()).isSameAs(brand);
        assertThat(result.getCategory()).isSameAs(category);
        assertThat(result.getSellingPriceM2())
                .isEqualByComparingTo("10.00");
        assertThat(result.getCostPriceM2())
                .isEqualByComparingTo("6.90");
        assertThat(result.getIsActive()).isTrue();

        verify(productRepository).save(any(Product.class));
    }

    @Test
    void createProduct_shouldThrowException_whenBrandDoesNotExist() {
        when(brandRepository.findById(1))
                .thenReturn(Optional.empty());

        when(categoryRepository.findById(1))
                .thenReturn(Optional.of(category));

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> productService.createProduct(productDTO)
        );

        assertThat(exception.getMessage()).isEqualTo("Brand not found");

        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void createProduct_shouldThrowException_whenCategoryDoesNotExist() {
        when(brandRepository.findById(1))
                .thenReturn(Optional.of(brand));

        when(categoryRepository.findById(1))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> productService.createProduct(productDTO)
        );

        assertThat(exception.getMessage()).isEqualTo("Category not found");

        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void updateProduct_shouldUpdateProduct_whenProductExists() {
        when(productRepository.findById(1))
                .thenReturn(Optional.of(product));

        when(brandRepository.findById(1))
                .thenReturn(Optional.of(brand));

        when(categoryRepository.findById(1))
                .thenReturn(Optional.of(category));

        when(productRepository.save(any(Product.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        productDTO.setName("Updated Product");
        productDTO.setSellingPriceM2(new BigDecimal("12.50"));

        Product result = productService.updateProduct(1, productDTO);

        assertThat(result.getName()).isEqualTo("Updated Product");
        assertThat(result.getSellingPriceM2())
                .isEqualByComparingTo("12.50");

        verify(productRepository).save(product);
    }

    @Test
    void updateProduct_shouldThrowException_whenBrandDoesNotExist() {
        when(productRepository.findById(1))
                .thenReturn(Optional.of(product));

        when(brandRepository.findById(1))
                .thenReturn(Optional.empty());

        when(categoryRepository.findById(1))
                .thenReturn(Optional.of(category));

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> productService.updateProduct(1, productDTO)
        );

        assertThat(exception.getMessage()).isEqualTo("Brand not found");

        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void updateProduct_shouldThrowException_whenCategoryDoesNotExist() {
        when(productRepository.findById(1))
                .thenReturn(Optional.of(product));

        when(brandRepository.findById(1))
                .thenReturn(Optional.of(brand));

        when(categoryRepository.findById(1))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> productService.updateProduct(1, productDTO)
        );

        assertThat(exception.getMessage()).isEqualTo("Category not found");

        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void updateProduct_shouldThrowException_whenProductDoesNotExist() {
        when(productRepository.findById(99))
                .thenReturn(Optional.empty());

        when(brandRepository.findById(1))
                .thenReturn(Optional.of(brand));

        when(categoryRepository.findById(1))
                .thenReturn(Optional.of(category));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> productService.updateProduct(99, productDTO)
        );

        assertThat(exception.getMessage()).isEqualTo("Product not found");

        verify(productRepository, never()).save(any(Product.class));
    }
    
    @Test
    void deleteProduct_shouldDeleteProduct_whenProductExists() {
        when(productRepository.findById(1))
                .thenReturn(Optional.of(product));

        productService.deleteProduct(1);

        verify(productRepository).delete(product);
    }

    @Test
    void deleteProduct_shouldThrowException_whenProductDoesNotExist() {
        when(productRepository.findById(99))
                .thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> productService.deleteProduct(99)
        );

        assertThat(exception.getMessage()).isEqualTo("Product not found");

        verify(productRepository, never()).delete(any(Product.class));
    }
}

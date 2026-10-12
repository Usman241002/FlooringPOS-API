package ukhalid.dev.flooringpos_api.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import ukhalid.dev.flooringpos_api.entities.Brand;
import ukhalid.dev.flooringpos_api.entities.Category;
import ukhalid.dev.flooringpos_api.entities.Product;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private Product seedProduct() {
        Brand brand = new Brand();
        brand.setName("Test Brand");
        brand = brandRepository.save(brand);

        Category category = new Category();
        category.setName("Test Category");
        category = categoryRepository.save(category);

        Product product = new Product();
        product.setBrand(brand);
        product.setCategory(category);
        product.setName("Test Product");
        product.setCostPriceM2(new BigDecimal("6.90"));
        product.setSellingPriceM2(new BigDecimal("10.00"));
        product.setIsActive(true);

        return productRepository.save(product);
    }

    private List<Product> seedProducts() {
        Brand brand = new Brand();
        brand.setName("Test Brand");
        brand = brandRepository.save(brand);

        Category category = new Category();
        category.setName("Test Category");
        category = categoryRepository.save(category);

        Product product1 = new Product();
        product1.setBrand(brand);
        product1.setCategory(category);
        product1.setName("Test Product 1");
        product1.setCostPriceM2(BigDecimal.valueOf(6.90));
        product1.setSellingPriceM2(BigDecimal.valueOf(10.00));
        product1.setIsActive(true);

        Product product2 = new Product();
        product2.setBrand(brand);
        product2.setCategory(category);
        product2.setName("Test Product 2");
        product2.setCostPriceM2(BigDecimal.valueOf(8.50));
        product2.setSellingPriceM2(BigDecimal.valueOf(12.00));
        product2.setIsActive(true);

        Product product3 = new Product();
        product3.setBrand(brand);
        product3.setCategory(category);
        product3.setName("Inactive Product");
        product3.setCostPriceM2(new BigDecimal("4.00"));
        product3.setSellingPriceM2(new BigDecimal("7.00"));
        product3.setIsActive(false);

        return productRepository.saveAll(
                List.of(product1, product2, product3)
        );
    }

    @Test
    public void findById_shouldReturnProduct_whenProductExists() {
        Product savedProduct = seedProduct();

        Product product = productRepository.findById(savedProduct.getId()).orElse(null);

        assertThat(product).isNotNull();
        assertThat(product.getName()).isEqualTo("Test Product");
    }

    @Test
    public void findById_shouldReturnEmpty_whenProductDoesNotExists() {
        Product product = productRepository.findById(-1).orElse(null);

        assertThat(product).isNull();
    }

    @Test
    public void findAll_shouldReturnAllProducts_whenProductsExist() {
        List<Product> savedProducts = seedProducts();

        List<Product> products = productRepository.findAll();

        assertThat(products).hasSize(savedProducts.size());
    }

    @Test
    public void findAll_shouldReturnEmptyList_whenNoProductsExist() {
        List<Product> products = productRepository.findAll();

        assertThat(products).isEmpty();
    }


    @Test
    public void save_shouldPersistProduct_whenProductIsValid() {
        Product savedProduct = seedProduct();

        productRepository.flush();

        assertThat(productRepository.findById(savedProduct.getId()))
                .isPresent()
                .get()
                .extracting(Product::getName)
                .isEqualTo("Test Product");
    }

    @Test
    public void delete_shouldRemoveProduct_whenProductExists() {
        Product savedProduct = seedProduct();

        productRepository.delete(savedProduct);
        productRepository.flush();

        assertThat(productRepository.findById(savedProduct.getId()))
                .isEmpty();
    }
    
}
package ukhalid.dev.flooringpos_api.services;

import org.springframework.stereotype.Service;
import ukhalid.dev.flooringpos_api.dtos.CreateProductDTO;
import ukhalid.dev.flooringpos_api.entities.Brand;
import ukhalid.dev.flooringpos_api.entities.Category;
import ukhalid.dev.flooringpos_api.entities.Product;
import ukhalid.dev.flooringpos_api.repositories.BrandRepository;
import ukhalid.dev.flooringpos_api.repositories.CategoryRepository;
import ukhalid.dev.flooringpos_api.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, BrandRepository brandRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Product getProductById(Integer id) {
        return this.productRepository.findById(id).orElse(null);
    }

    public Product createProduct(CreateProductDTO product) {
        Product productToSave = new Product();

        Optional<Brand> brandOptional =
                brandRepository.findById(product.getBrand());

        Optional<Category> categoryOptional =
                categoryRepository.findById(product.getCategory());

        if (brandOptional.isEmpty()) {
            throw new RuntimeException("Brand not found");
        }

        if (categoryOptional.isEmpty()) {
            throw new RuntimeException("Category not found");
        }

        productToSave.setBrand(brandOptional.get());
        productToSave.setCategory(categoryOptional.get());
        productToSave.setName(product.getName());
        productToSave.setSellingPriceM2(product.getSellingPriceM2());
        productToSave.setCostPriceM2(product.getCostPriceM2());
        productToSave.setIsActive(product.getIsActive());

        return productRepository.save(productToSave);
    }

    public Product deleteProduct(Integer id) {
        Optional<Product> existingProduct = this.productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            this.productRepository.delete(product);
            return product;
        }
        throw new IllegalArgumentException("Product not found");
    }
    
}

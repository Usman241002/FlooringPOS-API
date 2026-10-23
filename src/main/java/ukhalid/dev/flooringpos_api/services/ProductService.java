package ukhalid.dev.flooringpos_api.services;

import org.springframework.stereotype.Service;
import ukhalid.dev.flooringpos_api.dtos.ProductDTO;
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

    public Product createProduct(ProductDTO product) {
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

    public Product updateProduct(Integer id, ProductDTO product) {
        Optional<Product> productToUpdateOptional = this.productRepository.findById(id);

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

        if (productToUpdateOptional.isEmpty()) {
            throw new IllegalArgumentException("Product not found");
        }

        Product productToUpdate = productToUpdateOptional.get();
        Brand brand = brandOptional.get();
        Category category = categoryOptional.get();

        productToUpdate.setBrand(brand);
        productToUpdate.setCategory(category);
        productToUpdate.setName(product.getName());
        productToUpdate.setSellingPriceM2(product.getSellingPriceM2());
        productToUpdate.setCostPriceM2(product.getCostPriceM2());
        productToUpdate.setIsActive(product.getIsActive());

        return productRepository.save(productToUpdate);
    }

    public void deleteProduct(Integer id) {
        Optional<Product> existingProduct = this.productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            this.productRepository.delete(product);
        }
        throw new IllegalArgumentException("Product not found");
    }


}

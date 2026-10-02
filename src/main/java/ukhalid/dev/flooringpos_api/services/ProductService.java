package ukhalid.dev.flooringpos_api.services;

import org.springframework.stereotype.Service;
import ukhalid.dev.flooringpos_api.entities.Product;
import ukhalid.dev.flooringpos_api.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Product getProductById(Integer id) {
        return this.productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product) {
        return this.productRepository.save(product);
    }

    public Product updateProduct(Integer id, Product product) {
        Optional<Product> productToUpdateOptional = this.productRepository.findById(id);

        if (productToUpdateOptional.isPresent()) {
            Product productToUpdate = productToUpdateOptional.get();

            productToUpdate.setName(product.getName());
            productToUpdate.setPrice(product.getPrice());
            productToUpdate.setStockQuantity(product.getStockQuantity());
            return this.productRepository.save(productToUpdate);
        }
        throw new IllegalArgumentException("Product not found");
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

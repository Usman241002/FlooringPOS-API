package ukhalid.dev.flooringpos_api.controllers;

import org.springframework.web.bind.annotation.*;
import ukhalid.dev.flooringpos_api.entities.Product;
import ukhalid.dev.flooringpos_api.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable Integer id) {
        return productService.deleteProduct(id);
    }

}

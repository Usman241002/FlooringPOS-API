package ukhalid.dev.flooringpos_api.services;

import org.springframework.stereotype.Service;
import ukhalid.dev.flooringpos_api.entities.Category;
import ukhalid.dev.flooringpos_api.repositories.CategoryRepository;

import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public void deleteCategory(Integer id) {
        Optional<Category> categoryToDeleteOptional = categoryRepository.findById(id);
        if (categoryToDeleteOptional.isPresent()) {
            Category categoryToDelete = categoryToDeleteOptional.get();
            categoryRepository.delete(categoryToDelete);
        }
        throw new IllegalArgumentException("Category not found");
    }
}

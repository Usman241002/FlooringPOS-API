package ukhalid.dev.flooringpos_api.controllers;

import org.springframework.web.bind.annotation.*;
import ukhalid.dev.flooringpos_api.entities.Category;
import ukhalid.dev.flooringpos_api.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public Iterable<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Integer id) {
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
    }
}

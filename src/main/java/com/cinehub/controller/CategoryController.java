package com.cinehub.controller;

import com.cinehub.dto.CategoryDTO;
import com.cinehub.model.Category;
import com.cinehub.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        Category createdCategory = categoryService.create(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    /**
     * GET /api/categories
     */
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAll();
        return ResponseEntity.ok(categories);
    }

    /**
     * GET /api/categories/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        return ResponseEntity.ok(category);
    }

    /**
     * Mettre à jour une catégorie existante
     * PUT /api/categories/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryDTO categoryDTO) {
        Category updatedCategory = categoryService.update(id, categoryDTO);
        return ResponseEntity.ok(updatedCategory);
    }

    /**
     * Supprimer une catégorie
     * DELETE /api/categories/{id}
     * @param id L'identifiant de la catégorie à supprimer
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
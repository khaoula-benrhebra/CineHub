// src/main/java/com/cinehub/service/CategoryService.java
package com.cinehub.service;

import com.cinehub.model.Category;
import com.cinehub.dto.CategoryDTO;
import com.cinehub.repository.CategoryRepository;
import com.cinehub.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        // Vérifier si la catégorie existe déjà
        if (categoryRepository.existsByName(categoryDTO.getName())) {
            throw new IllegalArgumentException("Une catégorie avec le nom '" + categoryDTO.getName() + "' existe déjà.");
        }

        Category category = categoryMapper.toEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.toDTO(savedCategory);
    }

    @Transactional(readOnly = true)
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<CategoryDTO> getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public Optional<CategoryDTO> getCategoryByName(String name) {
        return categoryRepository.findByName(name)
                .map(categoryMapper::toDTO);
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Catégorie non trouvée avec l'ID : " + id));

        // Vérifier si le nouveau nom n'existe pas déjà sauf pour la catégorie actuelle
        if (!existingCategory.getName().equals(categoryDTO.getName()) &&
                categoryRepository.existsByName(categoryDTO.getName())) {
            throw new IllegalArgumentException("Une catégorie avec le nom '" + categoryDTO.getName() + "' existe déjà.");
        }

        // Mise à jour des champs
        existingCategory.setName(categoryDTO.getName());
        existingCategory.setDescription(categoryDTO.getDescription());

        Category updatedCategory = categoryRepository.save(existingCategory);

        return categoryMapper.toDTO(updatedCategory);
    }

    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Catégorie non trouvée avec l'ID : " + id));

        categoryRepository.delete(category);
    }

    @Transactional(readOnly = true)
    public List<CategoryDTO> searchCategoriesByName(String name) {
        return categoryRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }
}
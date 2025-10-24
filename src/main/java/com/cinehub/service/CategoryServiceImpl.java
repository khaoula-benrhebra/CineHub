package com.cinehub.service;


import com.cinehub.dto.CategoryDTO;
import com.cinehub.exception.CategoryAlreadyExistsException;
import com.cinehub.exception.CategoryNotFoundException;
import com.cinehub.mapper.CategoryMapper;
import com.cinehub.model.Category;
import com.cinehub.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Category create(CategoryDTO categoryDTO) {
        if (categoryRepository.existsByName(categoryDTO.getName())) {
            throw new CategoryAlreadyExistsException(
                    "Une catégorie avec le nom '" + categoryDTO.getName() + "' existe déjà"
            );
        }

        Category category = categoryMapper.toEntity(categoryDTO);
        return categoryRepository.save(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Category getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Catégorie non trouvée avec l'ID : " + id
                ));
    }

    @Override
    public Category update(Long id, CategoryDTO categoryDTO) {
        Category existingCategory = getById(id);

        if (!existingCategory.getName().equals(categoryDTO.getName())
                && categoryRepository.existsByName(categoryDTO.getName())) {
            throw new CategoryAlreadyExistsException(
                    "Une catégorie avec le nom '" + categoryDTO.getName() + "' existe déjà"
            );
        }

        existingCategory.setName(categoryDTO.getName());
        existingCategory.setDescription(categoryDTO.getDescription());

        return categoryRepository.save(existingCategory);
    }

    @Override
    public void delete(Long id) {
        Category category = getById(id);
        categoryRepository.delete(category);
    }
}

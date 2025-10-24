package com.cinehub.service;

import com.cinehub.dto.CategoryDTO;
import com.cinehub.model.Category;

import java.util.List;

public interface CategoryService {

    Category create(CategoryDTO categoryDTO);
    List<Category> getAll();
    Category getById(Long id);
    Category update(Long id, CategoryDTO categoryDTO);
    void delete(Long id);
}
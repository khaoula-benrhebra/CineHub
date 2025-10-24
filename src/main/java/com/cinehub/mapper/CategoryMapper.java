package com.cinehub.mapper;

import com.cinehub.model.Category;
import com.cinehub.dto.CategoryDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryDTO toDTO(Category category) {
        if (category == null) {
            return null;
        }

        return new CategoryDTO(
                category.getName(),
                category.getDescription()
        );
    }

    public Category toEntity(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            return null;
        }

        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        return category;
    }
}
package com.inventory.inventorySystem.mapper;

import com.inventory.inventorySystem.dto.request.CategoryRequest;
import com.inventory.inventorySystem.dto.response.CategoryResponse;
import com.inventory.inventorySystem.mapper.interfaces.CategoryMapper;
import com.inventory.inventorySystem.model.Category;
import com.inventory.inventorySystem.utils.StringNormalizer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapperImp implements CategoryMapper {

    private final StringNormalizer stringNormalizer;

    @Override
    public Category toEntity(CategoryRequest categoryRequest) {
        var category = new Category();
        category.setName(stringNormalizer.toTitleCase(categoryRequest.name()));
        category.setStatus(categoryRequest.status());
        return category;
    }

    @Override
    public CategoryResponse toDto(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getCreationDate(),
                category.getStatus()
        );
    }

    @Override
    public void applyPartialUpdate(Category category, CategoryRequest categoryRequest) {
        if (categoryRequest.name() != null) {
            category.setName(stringNormalizer.toTitleCase(categoryRequest.name()));
        }
        if (categoryRequest.status() != null) {
            category.setStatus(categoryRequest.status());
        }
    }
}

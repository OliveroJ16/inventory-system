package com.inventory.inventorySystem.mapper;

import com.inventory.inventorySystem.dto.request.CategoryRequest;
import com.inventory.inventorySystem.dto.response.CategoryResponse;
import com.inventory.inventorySystem.mapper.interfaces.CategoryMapper;
import com.inventory.inventorySystem.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapperImp implements CategoryMapper {

    @Override
    public Category toEntity(CategoryRequest categoryRequest) {
        var category = new Category();
        category.setName(categoryRequest.name());
        category.setStatus(true);
        return category;
    }

    @Override
    public CategoryResponse toDtos(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getCreationDate(),
                category.getStatus()
        );
    }
}

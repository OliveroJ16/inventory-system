package com.inventory.inventorySystem.mapper.interfaces;

import com.inventory.inventorySystem.dto.request.CategoryRequest;
import com.inventory.inventorySystem.dto.response.CategoryResponse;
import com.inventory.inventorySystem.model.Category;

public interface CategoryMapper {
    Category toEntity(CategoryRequest categoryRequest);
    CategoryResponse toDtos(Category category);
}

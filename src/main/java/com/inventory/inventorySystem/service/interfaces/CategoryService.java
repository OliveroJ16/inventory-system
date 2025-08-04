package com.inventory.inventorySystem.service.interfaces;

import com.inventory.inventorySystem.dto.request.CategoryRequest;
import com.inventory.inventorySystem.dto.response.CategoryResponse;

public interface CategoryService {
    CategoryResponse saveCategory(CategoryRequest categoryRequest);
}

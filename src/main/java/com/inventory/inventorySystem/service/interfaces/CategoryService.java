package com.inventory.inventorySystem.service.interfaces;

import com.inventory.inventorySystem.dto.request.CategoryRequest;
import com.inventory.inventorySystem.dto.response.CategoryResponse;
import com.inventory.inventorySystem.dto.response.PaginatedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CategoryService {
    CategoryResponse saveCategory(CategoryRequest categoryRequest);
    CategoryResponse updateCategory(UUID id, CategoryRequest categoryRequest);
    PaginatedResponse<CategoryResponse> getAllCategories(String name, Pageable pageable);
}

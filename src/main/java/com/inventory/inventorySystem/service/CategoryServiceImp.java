package com.inventory.inventorySystem.service;

import com.inventory.inventorySystem.dto.request.CategoryRequest;
import com.inventory.inventorySystem.dto.response.CategoryResponse;
import com.inventory.inventorySystem.mapper.interfaces.CategoryMapper;
import com.inventory.inventorySystem.model.Category;
import com.inventory.inventorySystem.repository.CategoryRepository;
import com.inventory.inventorySystem.service.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
        Category category = categoryMapper.toEntity(categoryRequest);
        Category categorySaved = categoryRepository.save(category);
        return categoryMapper.toDtos(categorySaved);
    }
}

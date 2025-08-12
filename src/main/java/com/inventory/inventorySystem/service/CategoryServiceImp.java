package com.inventory.inventorySystem.service;

import com.inventory.inventorySystem.dto.request.CategoryRequest;
import com.inventory.inventorySystem.dto.response.CategoryResponse;
import com.inventory.inventorySystem.dto.response.PaginatedResponse;
import com.inventory.inventorySystem.mapper.interfaces.CategoryMapper;
import com.inventory.inventorySystem.model.Category;
import com.inventory.inventorySystem.repository.CategoryRepository;
import com.inventory.inventorySystem.service.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
        Category category = categoryMapper.toEntity(categoryRequest);
        Category categorySaved = categoryRepository.save(category);
        return categoryMapper.toDto(categorySaved);
    }

    @Override
    public CategoryResponse updateCategory(UUID id, CategoryRequest categoryRequest){
        Category category = categoryRepository.findById(id).orElseThrow();
        categoryMapper.applyPartialUpdate(category, categoryRequest);
        categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }

    @Override
    public PaginatedResponse<CategoryResponse> getAllCategories(String name, Pageable pageable) {
        Page<Category> categoryPage;
        if (name != null && !name.trim().isEmpty()) {
            categoryPage = categoryRepository.findByNameContainingIgnoreCase(name, pageable);
        } else {
            categoryPage = categoryRepository.findAll(pageable);
        }
        Page<CategoryResponse> responsePage = categoryPage.map(categoryMapper::toDto);
        return new PaginatedResponse<>(responsePage);
    }

}

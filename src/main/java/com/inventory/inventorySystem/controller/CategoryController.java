package com.inventory.inventorySystem.controller;

import com.inventory.inventorySystem.dto.request.CategoryRequest;
import com.inventory.inventorySystem.dto.response.CategoryResponse;
import com.inventory.inventorySystem.service.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody CategoryRequest categoryRequest){
        CategoryResponse categoryResponse = categoryService.saveCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable UUID id, @RequestBody CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse = categoryService.updateCategory(id, categoryRequest);
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponse);
    }

}

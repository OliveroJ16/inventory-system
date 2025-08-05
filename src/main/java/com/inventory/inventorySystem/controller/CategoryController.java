package com.inventory.inventorySystem.controller;

import com.inventory.inventorySystem.dto.OnCreate;
import com.inventory.inventorySystem.dto.OnUpdate;
import com.inventory.inventorySystem.dto.request.CategoryRequest;
import com.inventory.inventorySystem.dto.response.CategoryResponse;
import com.inventory.inventorySystem.dto.response.PaginatedResponse;
import com.inventory.inventorySystem.service.interfaces.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> saveCategory(@Validated(OnCreate.class) @RequestBody CategoryRequest categoryRequest){
        CategoryResponse categoryResponse = categoryService.saveCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable UUID id, @Validated(OnUpdate.class)  @RequestBody CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse = categoryService.updateCategory(id, categoryRequest);
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponse);
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<CategoryResponse>>getAllCategories(@RequestParam(required = false) String name, @PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        PaginatedResponse<CategoryResponse> categoryResponse = categoryService.getAllCategories(name, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponse);
    }

}

package com.inventory.inventorySystem.controller;

import com.inventory.inventorySystem.dto.OnCreate;
import com.inventory.inventorySystem.dto.OnUpdate;
import com.inventory.inventorySystem.dto.request.ArticleRequest;
import com.inventory.inventorySystem.dto.response.ArticleResponse;
import com.inventory.inventorySystem.dto.response.PaginatedResponse;
import com.inventory.inventorySystem.service.interfaces.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<ArticleResponse> saveArticle(@Validated(OnCreate.class) @RequestBody ArticleRequest articleRequest){
        ArticleResponse articleResponse = articleService.saveArticle(articleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(articleResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ArticleResponse> updateCategory(@PathVariable UUID id, @Validated(OnUpdate.class)  @RequestBody ArticleRequest articleRequest){
        ArticleResponse articleResponse = articleService.updateArticle(id, articleRequest);
        return ResponseEntity.status(HttpStatus.OK).body(articleResponse);
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<ArticleResponse>> getAllArticles(@RequestParam(required = false) String name, @PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable){
        PaginatedResponse<ArticleResponse> articleResponse = articleService.getAllArticles(name, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(articleResponse);
    }

}

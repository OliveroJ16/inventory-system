package com.inventory.inventorySystem.controller;

import com.inventory.inventorySystem.dto.OnCreate;
import com.inventory.inventorySystem.dto.request.ArticleRequest;
import com.inventory.inventorySystem.dto.response.ArticleResponse;
import com.inventory.inventorySystem.service.interfaces.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

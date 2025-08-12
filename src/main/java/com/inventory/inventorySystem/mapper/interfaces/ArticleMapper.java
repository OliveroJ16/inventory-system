package com.inventory.inventorySystem.mapper.interfaces;

import com.inventory.inventorySystem.dto.request.ArticleRequest;
import com.inventory.inventorySystem.dto.response.ArticleResponse;
import com.inventory.inventorySystem.model.Article;
import com.inventory.inventorySystem.model.Category;

public interface ArticleMapper {
    Article toEntity(ArticleRequest articleRequest, Category category);
    ArticleResponse toDto(Article article);
    void applyPartialUpdate(Article article, ArticleRequest articleRequest);
}

package com.inventory.inventorySystem.service.interfaces;

import com.inventory.inventorySystem.dto.request.ArticleRequest;
import com.inventory.inventorySystem.dto.response.ArticleResponse;

import java.util.UUID;

public interface ArticleService {
    ArticleResponse saveArticle(ArticleRequest articleRequest);
    ArticleResponse updateArticle(UUID id, ArticleRequest articleRequest);
}

package com.inventory.inventorySystem.service.interfaces;

import com.inventory.inventorySystem.dto.request.ArticleRequest;
import com.inventory.inventorySystem.dto.response.ArticleResponse;

public interface ArticleService {
    ArticleResponse saveArticle(ArticleRequest articleRequest);
}

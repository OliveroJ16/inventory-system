package com.inventory.inventorySystem.service.interfaces;

import com.inventory.inventorySystem.dto.request.ArticleRequest;
import com.inventory.inventorySystem.dto.response.ArticleResponse;
import com.inventory.inventorySystem.dto.response.PaginatedResponse;
import com.inventory.inventorySystem.model.Article;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface ArticleService {
    ArticleResponse saveArticle(ArticleRequest articleRequest);
    ArticleResponse updateArticle(UUID id, ArticleRequest articleRequest);
    PaginatedResponse<ArticleResponse> getAllArticles(String name, Pageable pageable);
    Article updateStock(UUID id, Integer quantity);
}

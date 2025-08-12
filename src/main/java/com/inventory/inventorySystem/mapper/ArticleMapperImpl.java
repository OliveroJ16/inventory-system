package com.inventory.inventorySystem.mapper;

import com.inventory.inventorySystem.dto.request.ArticleRequest;
import com.inventory.inventorySystem.dto.response.ArticleResponse;
import com.inventory.inventorySystem.mapper.interfaces.ArticleMapper;
import com.inventory.inventorySystem.model.Article;
import com.inventory.inventorySystem.model.Category;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ArticleMapperImpl implements ArticleMapper {

    @Override
    public Article toEntity(ArticleRequest articleRequest, Category category) {
        var article = new Article();
        article.setName(articleRequest.name());
        article.setUnitPrice(articleRequest.unitPrice());
        article.setStock(articleRequest.stock());
        article.setDescription(articleRequest.description());
        article.setUnitOfMeasurement(articleRequest.unitOfMeasurement());
        article.setStatus(articleRequest.status());
        article.setImageUrl(articleRequest.imageUrl());
        article.setContent(articleRequest.content());
        article.setCategory(category);
        return article;
    }

    @Override
    public ArticleResponse toDto(Article article) {
        return new ArticleResponse(
                article.getId(),
                article.getName(),
                article.getUnitPrice(),
                article.getStock(),
                article.getDescription(),
                article.getUnitOfMeasurement(),
                article.getCreationDate(),
                article.getStatus(),
                article.getImageUrl(),
                article.getContent(),
                article.getCategory().getId()
        );
    }

    @Override
    public void applyPartialUpdate(Article article, ArticleRequest articleRequest) {
        if (articleRequest.name() != null) {
            article.setName(articleRequest.name());
        }
        if (articleRequest.unitPrice() != null) {
            article.setUnitPrice(articleRequest.unitPrice());
        }
        if (articleRequest.stock() != null) {
            article.setStock(articleRequest.stock());
        }
        if (articleRequest.description() != null) {
            article.setDescription(articleRequest.description());
        }
        if (articleRequest.unitOfMeasurement() != null) {
            article.setUnitOfMeasurement(articleRequest.unitOfMeasurement());
        }
        if (articleRequest.status() != null) {
            article.setStatus(articleRequest.status());
        }
        if (articleRequest.imageUrl() != null) {
            article.setImageUrl(articleRequest.imageUrl());
        }
        if (articleRequest.content() != null) {
            article.setContent(articleRequest.content());
        }
    }
}

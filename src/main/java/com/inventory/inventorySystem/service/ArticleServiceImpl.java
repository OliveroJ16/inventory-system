package com.inventory.inventorySystem.service;

import com.inventory.inventorySystem.dto.request.ArticleRequest;
import com.inventory.inventorySystem.dto.response.ArticleResponse;
import com.inventory.inventorySystem.exceptions.ResourceNotFoundException;
import com.inventory.inventorySystem.mapper.interfaces.ArticleMapper;
import com.inventory.inventorySystem.model.Article;
import com.inventory.inventorySystem.model.Category;
import com.inventory.inventorySystem.repository.ArticleRepository;
import com.inventory.inventorySystem.repository.CategoryRepository;
import com.inventory.inventorySystem.service.interfaces.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public ArticleResponse saveArticle(ArticleRequest articleRequest){
        Category category = categoryRepository.findById(articleRequest.id_category())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", articleRequest.id_category()));
        Article article = articleMapper.toEntity(articleRequest, category);
        Article articleSaved = articleRepository.save(article);
        return articleMapper.toDto(articleSaved);
    }

    @Override
    public ArticleResponse updateArticle(UUID id, ArticleRequest articleRequest){
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article", "id", id));
        articleMapper.applyPartialUpdate(article, articleRequest);
        articleRepository.save(article);
        return articleMapper.toDto(article);
    }
}

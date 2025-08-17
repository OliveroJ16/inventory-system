package com.inventory.inventorySystem.service;

import com.inventory.inventorySystem.dto.request.SaleDetailRequest;
import com.inventory.inventorySystem.dto.response.SaleDetailResponse;
import com.inventory.inventorySystem.exceptions.ResourceNotFoundException;
import com.inventory.inventorySystem.exceptions.StockExhaustedException;
import com.inventory.inventorySystem.mapper.interfaces.SaleDetailMapper;
import com.inventory.inventorySystem.model.Article;
import com.inventory.inventorySystem.model.Sale;
import com.inventory.inventorySystem.model.SaleDetail;
import com.inventory.inventorySystem.repository.ArticleRepository;
import com.inventory.inventorySystem.repository.SaleDetailRepository;
import com.inventory.inventorySystem.service.interfaces.SaleDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleDetailServiceImpl implements SaleDetailService {

    private final SaleDetailRepository saleDetailRepository;
    private final SaleDetailMapper saleDetailMapper;
    private final ArticleRepository articleRepository;

    @Transactional
    @Override
    public List<SaleDetailResponse> registerSaleDetail(List<SaleDetailRequest> saleDetailRequest, Sale sale){

        List<UUID> articleIds = saleDetailRequest.stream()
                .map(SaleDetailRequest::articleId)
                .toList();

        List<Article> articles = articleRepository.findAllById(articleIds);

        Map<UUID, Article> articleMap = articles.stream()
                .collect(Collectors.toMap(Article::getId, a -> a));

        List<Article> articlesToUpdate = new ArrayList<>();
        List<SaleDetail> details = new ArrayList<>();

        saleDetailRequest.forEach(detailRequest -> {
            Article article = articleMap.get(detailRequest.articleId());
            if (article == null) {
                throw new ResourceNotFoundException("Article", "id", detailRequest.articleId());
            }
            if (article.getStock() <= 0) {
                throw new StockExhaustedException("There is no stock available for the article: " + article.getName());
            }

            article.setStock(article.getStock() - detailRequest.quantity());

            articlesToUpdate.add(article);
            details.add(saleDetailMapper.toEntity(detailRequest, article, sale));
        });

        articleRepository.saveAll(articlesToUpdate);
        saleDetailRepository.saveAll(details);

        return details.stream()
                .map(saleDetailMapper::toDto)
                .toList();
    }
}

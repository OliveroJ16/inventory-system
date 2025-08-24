package com.inventory.inventorySystem.service;

import com.inventory.inventorySystem.dto.request.SaleDetailRequest;
import com.inventory.inventorySystem.dto.response.SaleDetailResponse;
import com.inventory.inventorySystem.mapper.interfaces.SaleDetailMapper;
import com.inventory.inventorySystem.model.Article;
import com.inventory.inventorySystem.model.Sale;
import com.inventory.inventorySystem.model.SaleDetail;
import com.inventory.inventorySystem.repository.SaleDetailRepository;
import com.inventory.inventorySystem.service.interfaces.ArticleService;
import com.inventory.inventorySystem.service.interfaces.SaleDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleDetailServiceImpl implements SaleDetailService {

    private final SaleDetailRepository saleDetailRepository;
    private final SaleDetailMapper saleDetailMapper;
    private final ArticleService articleService;

    @Transactional
    @Override
    public List<SaleDetailResponse> registerSaleDetail(List<SaleDetailRequest> saleDetailRequest, Sale sale){
        var details = new ArrayList<SaleDetail>();

        saleDetailRequest.forEach(detailRequest -> {
            Article article = articleService.updateStock(detailRequest.articleId(), detailRequest.quantity());
            details.add(saleDetailMapper.toEntity(detailRequest, article, sale));
        });

        saleDetailRepository.saveAll(details);

        return details.stream()
                .map(saleDetailMapper::toDto)
                .toList();
    }
}

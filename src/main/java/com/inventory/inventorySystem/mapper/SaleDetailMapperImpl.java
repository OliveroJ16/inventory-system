package com.inventory.inventorySystem.mapper;

import com.inventory.inventorySystem.dto.request.SaleDetailRequest;
import com.inventory.inventorySystem.dto.response.SaleDetailResponse;
import com.inventory.inventorySystem.mapper.interfaces.SaleDetailMapper;
import com.inventory.inventorySystem.model.Article;
import com.inventory.inventorySystem.model.Sale;
import com.inventory.inventorySystem.model.SaleDetail;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SaleDetailMapperImpl implements SaleDetailMapper {

    @Override
    public SaleDetail toEntity(SaleDetailRequest saleDetailRequest, Article article, Sale sale){
        var saleDetail = new SaleDetail();
        saleDetail.setQuantity(saleDetailRequest.quantity());
        saleDetail.setUnitPrice(article.getUnitPrice());
        saleDetail.setSubtotal(article.getUnitPrice().multiply(BigDecimal.valueOf(saleDetailRequest.quantity())));
        saleDetail.setArticle(article);
        saleDetail.setSale(sale);
        return saleDetail;
    }

    @Override
    public SaleDetailResponse toDto(SaleDetail saleDetail) {
        return new SaleDetailResponse(
                saleDetail.getId(),
                saleDetail.getQuantity(),
                saleDetail.getUnitPrice(),
                saleDetail.getSubtotal(),
                saleDetail.getSale().getId(),
                saleDetail.getArticle().getId(),
                saleDetail.getArticle().getName()
        );
    }

}

package com.inventory.inventorySystem.mapper;

import com.inventory.inventorySystem.dto.request.PurchaseDetailRequest;
import com.inventory.inventorySystem.dto.response.PurchaseDetailResponse;
import com.inventory.inventorySystem.mapper.interfaces.PurchaseDetailMapper;
import com.inventory.inventorySystem.model.Article;
import com.inventory.inventorySystem.model.Purchase;
import com.inventory.inventorySystem.model.PurchaseDetail;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PurchaseDetailMapperImpl implements PurchaseDetailMapper {

    @Override
    public PurchaseDetail toEntity(PurchaseDetailRequest request, Article article, Purchase purchase) {
        var detail = new PurchaseDetail();
        detail.setQuantity(request.quantity());
        detail.setUnitPrice(article.getUnitPrice());
        detail.setSubtotal(article.getUnitPrice().multiply(BigDecimal.valueOf(request.quantity())));
        detail.setArticle(article);
        detail.setPurchase(purchase);
        return detail;
    }

    @Override
    public PurchaseDetailResponse toDto(PurchaseDetail detail) {
        return new PurchaseDetailResponse(
                detail.getId(),
                detail.getQuantity(),
                detail.getUnitPrice(),
                detail.getSubtotal(),
                detail.getPurchase().getId(),
                detail.getArticle().getId(),
                detail.getArticle().getName()
        );
    }
}

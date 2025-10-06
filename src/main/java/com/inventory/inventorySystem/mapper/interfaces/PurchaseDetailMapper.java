package com.inventory.inventorySystem.mapper.interfaces;

import com.inventory.inventorySystem.dto.request.PurchaseDetailRequest;
import com.inventory.inventorySystem.dto.response.PurchaseDetailResponse;
import com.inventory.inventorySystem.model.Article;
import com.inventory.inventorySystem.model.Purchase;
import com.inventory.inventorySystem.model.PurchaseDetail;

public interface PurchaseDetailMapper {
    PurchaseDetail toEntity(PurchaseDetailRequest request, Article article, Purchase purchase);
    PurchaseDetailResponse toDto(PurchaseDetail detail);
}

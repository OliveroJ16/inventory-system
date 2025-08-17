package com.inventory.inventorySystem.mapper.interfaces;

import com.inventory.inventorySystem.dto.request.SaleDetailRequest;
import com.inventory.inventorySystem.dto.response.SaleDetailResponse;
import com.inventory.inventorySystem.model.Article;
import com.inventory.inventorySystem.model.Sale;
import com.inventory.inventorySystem.model.SaleDetail;

public interface SaleDetailMapper {
    SaleDetailResponse toDto(SaleDetail saleDetail);
    SaleDetail toEntity(SaleDetailRequest saleDetailRequest, Article article, Sale sale);
}

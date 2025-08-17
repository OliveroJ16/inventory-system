package com.inventory.inventorySystem.service.interfaces;

import com.inventory.inventorySystem.dto.request.SaleDetailRequest;
import com.inventory.inventorySystem.dto.response.SaleDetailResponse;
import com.inventory.inventorySystem.model.Article;
import com.inventory.inventorySystem.model.Sale;

import java.util.List;

public interface SaleDetailService {
    List<SaleDetailResponse> registerSaleDetail(List<SaleDetailRequest> saleDetailRequest, Sale sale);
}

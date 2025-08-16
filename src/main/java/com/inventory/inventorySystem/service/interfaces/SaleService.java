package com.inventory.inventorySystem.service.interfaces;

import com.inventory.inventorySystem.dto.request.SaleRequest;
import com.inventory.inventorySystem.dto.response.SaleResponse;

public interface SaleService {
    SaleResponse registerSale(SaleRequest saleRequest);
}

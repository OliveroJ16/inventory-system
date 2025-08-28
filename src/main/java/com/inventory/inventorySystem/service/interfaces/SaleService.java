package com.inventory.inventorySystem.service.interfaces;

import com.inventory.inventorySystem.dto.request.SaleRequest;
import com.inventory.inventorySystem.dto.response.CompleteSaleResponse;
import com.inventory.inventorySystem.dto.response.PaginatedResponse;
import com.inventory.inventorySystem.dto.response.SaleResponse;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface SaleService {
    CompleteSaleResponse registerSale(SaleRequest saleRequest);
    PaginatedResponse<SaleResponse> getAllSales(LocalDate startDate, LocalDate endDate, String customerName, Pageable pageable);
}

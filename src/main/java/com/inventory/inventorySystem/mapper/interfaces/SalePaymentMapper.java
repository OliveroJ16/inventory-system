package com.inventory.inventorySystem.mapper.interfaces;

import com.inventory.inventorySystem.dto.request.SalePaymentRequest;
import com.inventory.inventorySystem.dto.response.SalePaymentResponse;
import com.inventory.inventorySystem.model.Sale;
import com.inventory.inventorySystem.model.SalePayment;

public interface SalePaymentMapper {
    SalePaymentResponse toDto(SalePayment salePayment);
    SalePayment toEntity(SalePaymentRequest request, Sale sale);
}


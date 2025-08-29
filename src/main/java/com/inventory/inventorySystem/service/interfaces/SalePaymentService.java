package com.inventory.inventorySystem.service.interfaces;

import com.inventory.inventorySystem.dto.request.SalePaymentRequest;
import com.inventory.inventorySystem.dto.response.SalePaymentResponse;
import com.inventory.inventorySystem.model.Sale;

import java.util.UUID;

public interface SalePaymentService {
    SalePaymentResponse saveSalePayment(SalePaymentRequest salePaymentRequest, Sale sale);
    SalePaymentResponse updateSalePayment(SalePaymentRequest salePaymentRequest, UUID id);
}

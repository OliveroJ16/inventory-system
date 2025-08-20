package com.inventory.inventorySystem.service.interfaces;

import com.inventory.inventorySystem.dto.request.SalePaymentRequest;
import com.inventory.inventorySystem.dto.response.SalePaymentResponse;
import com.inventory.inventorySystem.model.Sale;

public interface SalePaymentService {
    SalePaymentResponse saveSalePayment(SalePaymentRequest salePaymentRequest, Sale sale);
}

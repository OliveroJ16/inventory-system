package com.inventory.inventorySystem.mapper.interfaces;

import com.inventory.inventorySystem.dto.request.PurchasePaymentRequest;
import com.inventory.inventorySystem.dto.response.PurchasePaymentResponse;
import com.inventory.inventorySystem.model.Purchase;
import com.inventory.inventorySystem.model.PurchasePayment;

public interface PurchasePaymentMapper {
    PurchasePayment toEntity(PurchasePaymentRequest request, Purchase purchase);
    PurchasePaymentResponse toDto(PurchasePayment payment);
}

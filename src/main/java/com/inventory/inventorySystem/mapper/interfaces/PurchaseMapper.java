package com.inventory.inventorySystem.mapper.interfaces;

import com.inventory.inventorySystem.dto.response.CompletePurchaseResponse;
import com.inventory.inventorySystem.dto.response.PurchaseDetailResponse;
import com.inventory.inventorySystem.dto.response.PurchaseResponse;
import com.inventory.inventorySystem.model.Purchase;
import com.inventory.inventorySystem.model.Supplier;
import com.inventory.inventorySystem.model.User;

import java.util.List;

public interface PurchaseMapper {
    Purchase toEntity(User user, Supplier supplier);
    CompletePurchaseResponse toDto(Purchase purchase, List<PurchaseDetailResponse> details);
    PurchaseResponse toDto(Purchase purchase);
}

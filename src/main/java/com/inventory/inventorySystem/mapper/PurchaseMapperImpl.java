package com.inventory.inventorySystem.mapper;

import com.inventory.inventorySystem.dto.response.CompletePurchaseResponse;
import com.inventory.inventorySystem.dto.response.PurchaseDetailResponse;
import com.inventory.inventorySystem.dto.response.PurchaseResponse;
import com.inventory.inventorySystem.mapper.interfaces.PurchaseMapper;
import com.inventory.inventorySystem.model.Purchase;
import com.inventory.inventorySystem.model.Supplier;
import com.inventory.inventorySystem.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class PurchaseMapperImpl implements PurchaseMapper {

    @Override
    public Purchase toEntity(User user, Supplier supplier) {
        var purchase = new Purchase();
        purchase.setSupplier(supplier);
        purchase.setUser(user);
        return purchase;
    }

    @Override
    public CompletePurchaseResponse toDto(Purchase purchase, List<PurchaseDetailResponse> details) {
        UUID supplierId = null;
        String supplierName = null;

        if (purchase.getSupplier() != null) {
            supplierId = purchase.getSupplier().getId();
            supplierName = purchase.getSupplier().getFullName();
        }

        return new CompletePurchaseResponse(
                purchase.getId(),
                purchase.getPurchaseDate(),
                purchase.getTotalAmount(),
                purchase.getStatus(),
                supplierId,
                supplierName,
                purchase.getUser().getId(),
                purchase.getUser().getFullName(),
                details
        );
    }

    @Override
    public PurchaseResponse toDto(Purchase purchase) {
        UUID supplierId = null;
        String supplierName = null;
        if (purchase.getSupplier() != null) {
            supplierId = purchase.getSupplier().getId();
            supplierName = purchase.getSupplier().getFullName();
        }

        UUID userId = null;
        String userName = null;
        if (purchase.getUser() != null) {
            userId = purchase.getUser().getId();
            userName = purchase.getUser().getFullName();
        }

        return new PurchaseResponse(
                purchase.getId(),
                purchase.getPurchaseDate(),
                purchase.getTotalAmount(),
                purchase.getStatus(),
                supplierId,
                supplierName,
                userId,
                userName
        );
    }
}

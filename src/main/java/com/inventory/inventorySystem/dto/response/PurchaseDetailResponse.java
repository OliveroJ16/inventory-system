package com.inventory.inventorySystem.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record PurchaseDetailResponse(
        UUID id,
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal subtotal,

        UUID purchaseId,
        UUID articleId,
        String articleName
) { }

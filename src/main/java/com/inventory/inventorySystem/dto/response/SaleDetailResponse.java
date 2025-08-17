package com.inventory.inventorySystem.dto.response;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public record SaleDetailResponse(
        UUID id,
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal subtotal,
        UUID saleId,
        UUID articleId,
        String articleName
) {}

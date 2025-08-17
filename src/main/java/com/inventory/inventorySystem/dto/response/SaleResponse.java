package com.inventory.inventorySystem.dto.response;

import com.inventory.inventorySystem.enums.SaleStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record SaleResponse(
        UUID id,
        LocalDateTime date,
        BigDecimal totalSale,
        SaleStatus status,
        UUID customerId,
        String customerName,
        UUID userId,
        String userName,
        List<SaleDetailResponse> details
) {}

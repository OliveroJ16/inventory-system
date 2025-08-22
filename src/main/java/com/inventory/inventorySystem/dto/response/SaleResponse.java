package com.inventory.inventorySystem.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.inventory.inventorySystem.enums.SaleStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record SaleResponse(
        UUID id,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime date,

        BigDecimal totalSale,
        SaleStatus status,
        UUID customerId,
        String customerName,
        UUID userId,
        String userName,
        List<SaleDetailResponse> details
) {}

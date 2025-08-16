package com.inventory.inventorySystem.dto.request;

import com.inventory.inventorySystem.dto.OnCreate;
import com.inventory.inventorySystem.dto.OnUpdate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;
import java.util.UUID;

public record SaleRequest(
        @NotNull(message = "Total sale cannot be null", groups = OnCreate.class)
        @DecimalMin(value = "0.01", message = "Total sale must be greater than 0", groups = {OnCreate.class, OnUpdate.class})
        BigDecimal totalSale,

        @NotNull(message = "CustomerId cannot be null", groups = OnUpdate.class)
        UUID customerId,

        @NotNull(message = "UserId cannot be null", groups = OnCreate.class)
        UUID userId
) {}



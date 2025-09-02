package com.inventory.inventorySystem.dto.request;

import com.inventory.inventorySystem.dto.OnCreate;
import com.inventory.inventorySystem.enums.SaleStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record SaleRequest(

        UUID customerId,

        @NotNull(message = "UserId cannot be null", groups = OnCreate.class)
        UUID userId,

        @NotEmpty(message = "Sale must have at least one detail", groups = OnCreate.class)
        @Valid List<SaleDetailRequest> details
) { }



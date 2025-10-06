package com.inventory.inventorySystem.dto.request;

import com.inventory.inventorySystem.dto.OnCreate;
import com.inventory.inventorySystem.enums.SaleStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record PurchaseRequest(

        @NotNull(message = "Supplier ID is required", groups = OnCreate.class)
        UUID supplierId,

        @NotNull(message = "User ID is required", groups = OnCreate.class)
        UUID userId,

        @NotNull(message = "Status is required", groups = OnCreate.class)
        SaleStatus status,

        @NotEmpty(message = "Purchase must have at least one detail", groups = OnCreate.class)
        @Valid List<PurchaseDetailRequest> details,

        @Valid
        List<PurchasePaymentRequest> payments

) { }

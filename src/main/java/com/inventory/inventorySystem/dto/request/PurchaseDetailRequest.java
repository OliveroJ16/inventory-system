package com.inventory.inventorySystem.dto.request;

import com.inventory.inventorySystem.dto.OnCreate;
import com.inventory.inventorySystem.dto.OnUpdate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PurchaseDetailRequest(

        @NotNull(message = "Quantity is required", groups = OnCreate.class)
        @Min(value = 1, message = "Quantity must be at least 1", groups = {OnCreate.class, OnUpdate.class})
        Integer quantity,

        @NotNull(message = "Article ID is required", groups = OnCreate.class)
        UUID articleId

) { }

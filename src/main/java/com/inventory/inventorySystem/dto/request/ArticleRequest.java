package com.inventory.inventorySystem.dto.request;

import com.inventory.inventorySystem.dto.OnCreate;
import com.inventory.inventorySystem.dto.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

public record ArticleRequest(
        @NotBlank(message = "Name cannot be empty", groups = OnCreate.class)
        @Size(max = 50, message = "Name cannot exceed 50 characters", groups = {OnCreate.class, OnUpdate .class})
        String name,

        @NotNull(message = "Unit price is required", groups = OnCreate.class)
        @Positive(message = "Unit price must be a positive number", groups = {OnCreate.class, OnUpdate .class})
        BigDecimal unitPrice,

        @NotNull(message = "Stock is required", groups = OnCreate.class)
        @Positive(message = "Stock must be a positive number", groups = {OnCreate.class, OnUpdate .class})
        Integer stock,

        String description,

        @NotBlank(message = "Unit of measurement cannot be empty", groups = OnCreate.class)
        @Size(max = 50, message = "Unit of measurement cannot exceed 50 characters", groups = {OnCreate.class, OnUpdate .class})
        String unitOfMeasurement,

        @NotNull(message = "Status is required", groups = OnCreate.class)
        Boolean status,

        String imageUrl,

        @NotNull(message = "Content value is required", groups = OnCreate.class)
        @Positive(message = "Content value must be a positive number", groups = {OnCreate.class, OnUpdate .class})
        BigDecimal content,

        @NotNull(message = "Category ID is required", groups = OnCreate.class)
        UUID id_category
) { }

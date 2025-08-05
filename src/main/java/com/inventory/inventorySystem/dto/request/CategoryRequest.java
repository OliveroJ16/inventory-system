package com.inventory.inventorySystem.dto.request;

import com.inventory.inventorySystem.dto.OnCreate;
import com.inventory.inventorySystem.dto.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CategoryRequest(

        @NotBlank(message = "The category name is required", groups = OnCreate.class)
        @Size(max = 100, message = "The name must not exceed 100 characters", groups = {OnCreate.class, OnUpdate.class})
        String name,

        @NotNull(message = "The status must be specified", groups = OnCreate.class)
        Boolean status
) { }

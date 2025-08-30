package com.inventory.inventorySystem.dto.request;

import com.inventory.inventorySystem.dto.OnCreate;
import com.inventory.inventorySystem.dto.OnUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerRequest(

        @NotBlank(groups = {OnCreate.class}, message = "The name is required")
        @Size(max = 45, message = "The name cannot exceed 45 characters", groups = {OnCreate.class, OnUpdate.class})
        String name,

        @NotBlank(groups = {OnCreate.class}, message = "The last name is required")
        @Size(max = 45, message = "The last name cannot exceed 45 characters", groups = {OnCreate.class, OnUpdate.class})
        String lastName,

        @NotBlank(groups = {OnCreate.class}, message = "The phone number is required")
        @Size(max = 20, message = "The phone number cannot exceed 20 characters", groups = {OnCreate.class, OnUpdate.class})
        String phone,

        @Email(message = "The email must be valid", groups = {OnCreate.class, OnUpdate.class})
        @Size(max = 100, message = "The email cannot exceed 100 characters", groups = {OnCreate.class, OnUpdate.class})
        String email,

        @Size(max = 100, message = "The address cannot exceed 100 characters", groups = {OnCreate.class, OnUpdate.class})
        String address
) {}

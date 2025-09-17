package com.inventory.inventorySystem.dto.request;

import jakarta.validation.constraints.Email;
import com.inventory.inventorySystem.dto.OnUpdate;
import com.inventory.inventorySystem.dto.OnCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SupplierRequest(

        @NotBlank(groups = OnCreate.class, message = "Full name cannot be blank")
        @Size(max = 150, groups = {OnCreate.class, OnUpdate.class}, message = "Full name must not exceed 150 characters")
        String fullName,

        @NotBlank(groups = OnCreate.class, message = "Phone number cannot be blank")
        @Size(max = 20, groups = {OnCreate.class, OnUpdate.class}, message = "Phone number must not exceed 20 characters")
        String phone,

        @NotBlank(groups = OnCreate.class, message = "Email cannot be blank")
        @Email(groups = {OnCreate.class, OnUpdate.class}, message = "Email must be a valid format")
        @Size(max = 100, groups = {OnCreate.class, OnUpdate.class}, message = "Email must not exceed 100 characters")
        String email,

        @Size(max = 100, groups = {OnCreate.class, OnUpdate.class}, message = "Address must not exceed 100 characters")
        String address,

        @NotNull(groups = {OnCreate.class}, message = "Status is required when creating a supplier")
        Boolean status
) {}

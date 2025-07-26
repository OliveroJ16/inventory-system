package com.inventory.inventorySystem.dto.request;

public record RegisterRequest(
        String username,
        String fullName,
        String email,
        String password
) {}


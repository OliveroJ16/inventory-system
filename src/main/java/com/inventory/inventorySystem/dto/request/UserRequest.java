package com.inventory.inventorySystem.dto.request;

public record UserRequest(
        String username,
        String fullName,
        String email,
        String password
) {}


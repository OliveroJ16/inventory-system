package com.inventory.inventorySystem.dto.response;

public record AuthResponse(
        String accessToken,
        String refreshToken,
        UserResponse user
) { }

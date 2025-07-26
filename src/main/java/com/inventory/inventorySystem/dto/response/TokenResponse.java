package com.inventory.inventorySystem.dto.response;

public record TokenResponse(
        String accessToken,
        String refreshToken
) { }

package com.inventory.inventorySystem.dto.response;

import java.time.LocalDateTime;

public record ApiErrorResponse(
        int status,
        String error,
        Object message,
        String description,
        String path,
        LocalDateTime timestamp
) {}

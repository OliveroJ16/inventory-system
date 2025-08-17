package com.inventory.inventorySystem.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ApiErrorResponse(
        int status,
        String error,
        Object message,
        String description,
        String path,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime timestamp
) {}

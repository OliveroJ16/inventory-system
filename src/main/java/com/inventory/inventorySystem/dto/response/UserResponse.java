package com.inventory.inventorySystem.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.inventory.inventorySystem.enums.UserRole;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String userName,
        String fullName,
        String email,
        UserRole role,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime creationDate,

        Boolean status
) { }

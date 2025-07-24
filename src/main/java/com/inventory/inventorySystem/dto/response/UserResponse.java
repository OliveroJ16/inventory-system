package com.inventory.inventorySystem.dto.response;

import com.inventory.inventorySystem.enums.UserRole;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String userName,
        String fullName,
        String email,
        UserRole role,
        LocalDateTime creationDate,
        Boolean status
) { }

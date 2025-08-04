package com.inventory.inventorySystem.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record CategoryResponse(
        UUID id,
        String name,
        LocalDateTime creationDate,
        Boolean status
) { }

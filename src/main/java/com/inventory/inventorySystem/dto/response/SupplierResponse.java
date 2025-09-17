package com.inventory.inventorySystem.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record SupplierResponse(
        UUID id,
        String fullName,
        String phone,
        String email,
        String address,
        LocalDateTime registrationDate,
        Boolean status
) {}

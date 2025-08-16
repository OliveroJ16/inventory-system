package com.inventory.inventorySystem.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ArticleResponse(
        UUID id_article,
        String name,
        BigDecimal unitPrice,
        Integer stock,
        String description,
        String unitOfMeasurement,
        LocalDateTime creation_date,
        Boolean status,
        String imageUrl,
        BigDecimal content,
        UUID id_category,
        String nameCategory
) { }

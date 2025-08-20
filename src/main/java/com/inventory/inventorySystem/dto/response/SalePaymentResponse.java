package com.inventory.inventorySystem.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.inventory.inventorySystem.enums.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record SalePaymentResponse(
        UUID id,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime paymentDate,

        BigDecimal amountPaid,
        PaymentType paymentType,
        UUID saleId
) { }

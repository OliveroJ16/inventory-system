package com.inventory.inventorySystem.dto.request;

import com.inventory.inventorySystem.enums.PaymentType;
import com.inventory.inventorySystem.dto.OnCreate;
import com.inventory.inventorySystem.dto.OnUpdate;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PurchasePaymentRequest(

        @NotNull(message = "Amount paid is required", groups = OnCreate.class)
        @DecimalMin(value = "0.01", message = "Amount must be greater than zero", groups = {OnCreate.class, OnUpdate.class})
        BigDecimal amountPaid,

        @NotNull(message = "Payment type is required", groups = OnCreate.class)
        PaymentType paymentType

) { }

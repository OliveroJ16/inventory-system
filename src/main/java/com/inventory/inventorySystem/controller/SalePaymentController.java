package com.inventory.inventorySystem.controller;

import com.inventory.inventorySystem.dto.OnUpdate;
import com.inventory.inventorySystem.dto.request.SalePaymentRequest;
import com.inventory.inventorySystem.dto.response.SalePaymentResponse;
import com.inventory.inventorySystem.service.interfaces.SalePaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/salePayment")
public class SalePaymentController {

    private final SalePaymentService salePaymentService;

    @PatchMapping("/{id}")
    public ResponseEntity<SalePaymentResponse> updateSalePayment(@PathVariable UUID id, @Validated(OnUpdate.class) SalePaymentRequest salePaymentRequest){
        SalePaymentResponse salePaymentResponse = salePaymentService.updateSalePayment(salePaymentRequest, id);
        return ResponseEntity.status(HttpStatus.OK).body(salePaymentResponse);
    }
}

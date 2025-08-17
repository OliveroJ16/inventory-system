package com.inventory.inventorySystem.controller;

import com.inventory.inventorySystem.dto.OnCreate;
import com.inventory.inventorySystem.dto.request.SaleRequest;
import com.inventory.inventorySystem.dto.response.SaleResponse;
import com.inventory.inventorySystem.service.interfaces.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sales")
public class SaleController {

    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<SaleResponse> registerSale(@RequestBody  SaleRequest saleRequest){
        SaleResponse saleResponse = saleService.registerSale(saleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(saleResponse);
    }
}

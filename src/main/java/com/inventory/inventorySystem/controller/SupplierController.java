package com.inventory.inventorySystem.controller;

import com.inventory.inventorySystem.dto.OnCreate;
import com.inventory.inventorySystem.dto.request.SupplierRequest;
import com.inventory.inventorySystem.dto.response.SupplierResponse;
import com.inventory.inventorySystem.service.interfaces.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/suppliers")
public class SupplierController{

    private final SupplierService supplierService;

    @PostMapping
    public ResponseEntity<SupplierResponse> registerSupplier(@Validated(OnCreate.class) @RequestBody SupplierRequest supplierRequest){
        SupplierResponse supplierResponse = supplierService.saveSupplier(supplierRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierResponse);
    }
}

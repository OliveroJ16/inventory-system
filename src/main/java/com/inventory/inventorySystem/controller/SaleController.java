package com.inventory.inventorySystem.controller;

import com.inventory.inventorySystem.dto.request.SaleRequest;
import com.inventory.inventorySystem.dto.response.CompleteSaleResponse;
import com.inventory.inventorySystem.dto.response.PaginatedResponse;
import com.inventory.inventorySystem.dto.response.SaleResponse;
import com.inventory.inventorySystem.service.interfaces.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sales")
public class SaleController {

    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<CompleteSaleResponse> registerSale(@RequestBody  SaleRequest saleRequest){
        CompleteSaleResponse completeSaleResponse = saleService.registerSale(saleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(completeSaleResponse);
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<SaleResponse>> getAllSales(@RequestParam(required = false) LocalDateTime startDate, @RequestParam(required = false) LocalDateTime endDate, @PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC)Pageable pageable){
        PaginatedResponse<SaleResponse> saleResponses = saleService.getAllSales(startDate, endDate, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(saleResponses);
    }
}

package com.inventory.inventorySystem.controller;

import com.inventory.inventorySystem.dto.OnCreate;
import com.inventory.inventorySystem.dto.OnUpdate;
import com.inventory.inventorySystem.dto.request.CustomerRequest;
import com.inventory.inventorySystem.dto.response.CustomerResponse;
import com.inventory.inventorySystem.dto.response.PaginatedResponse;
import com.inventory.inventorySystem.service.interfaces.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> registerCustomer(@Validated(OnCreate.class) @RequestBody CustomerRequest customerRequest){
        CustomerResponse customerResponse = customerService.registerCustomer(customerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable UUID id, @Validated(OnUpdate.class) @RequestBody CustomerRequest customerRequest){
        CustomerResponse customerResponse = customerService.updateCustomer(id, customerRequest);
        return ResponseEntity.status(HttpStatus.OK).body(customerResponse);
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<CustomerResponse>> getAllCustomers(@RequestParam(required = false) String name, @PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable){
        PaginatedResponse<CustomerResponse> customerResponse = customerService.getAllCustomers(name, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(customerResponse);
    }
}

package com.inventory.inventorySystem.controller;

import com.inventory.inventorySystem.dto.OnCreate;
import com.inventory.inventorySystem.dto.request.CustomerRequest;
import com.inventory.inventorySystem.dto.response.CustomerResponse;
import com.inventory.inventorySystem.service.interfaces.CustomerService;
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
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> registerCustomer(@Validated(OnCreate.class) @RequestBody CustomerRequest customerRequest){
        CustomerResponse customerResponse = customerService.registerCustomer(customerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerResponse);
    }

}

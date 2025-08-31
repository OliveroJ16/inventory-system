package com.inventory.inventorySystem.service.interfaces;

import com.inventory.inventorySystem.dto.request.CustomerRequest;
import com.inventory.inventorySystem.dto.response.CustomerResponse;

import java.util.UUID;

public interface CustomerService {
    CustomerResponse registerCustomer(CustomerRequest customerRequest);
    CustomerResponse updateCustomer(UUID id, CustomerRequest customerRequest);
}

package com.inventory.inventorySystem.mapper.interfaces;

import com.inventory.inventorySystem.dto.request.CustomerRequest;
import com.inventory.inventorySystem.dto.response.CustomerResponse;
import com.inventory.inventorySystem.model.Customer;

public interface CustomerMapper {
    Customer toEntity(CustomerRequest request);
    CustomerResponse toResponse(Customer customer);
    void applyPartialUpdate(Customer customer, CustomerRequest request);
}

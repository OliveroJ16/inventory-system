package com.inventory.inventorySystem.mapper.interfaces;

import com.inventory.inventorySystem.dto.request.SaleRequest;
import com.inventory.inventorySystem.dto.response.SaleResponse;
import com.inventory.inventorySystem.model.Customer;
import com.inventory.inventorySystem.model.Sale;
import com.inventory.inventorySystem.model.User;

public interface SaleMapper {
    Sale toEntity(SaleRequest saleRequest, User user, Customer customer, boolean isCredit);
    SaleResponse toDto(Sale sale);
}

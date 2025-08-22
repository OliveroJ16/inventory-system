package com.inventory.inventorySystem.mapper.interfaces;

import com.inventory.inventorySystem.dto.response.SaleDetailResponse;
import com.inventory.inventorySystem.dto.response.SaleResponse;
import com.inventory.inventorySystem.model.Customer;
import com.inventory.inventorySystem.model.Sale;
import com.inventory.inventorySystem.model.User;

import java.util.List;

public interface SaleMapper {
    Sale toEntity(User user, Customer customer);
    SaleResponse toDto(Sale sale, List<SaleDetailResponse> details);
}

package com.inventory.inventorySystem.mapper;

import com.inventory.inventorySystem.dto.request.SaleRequest;
import com.inventory.inventorySystem.dto.response.SaleResponse;
import com.inventory.inventorySystem.enums.SaleStatus;
import com.inventory.inventorySystem.mapper.interfaces.SaleMapper;
import com.inventory.inventorySystem.model.Customer;
import com.inventory.inventorySystem.model.Sale;
import com.inventory.inventorySystem.model.User;

public class SaleMapperImpl implements SaleMapper {

    @Override
    public Sale toEntity(SaleRequest saleRequest, User user, Customer customer, boolean isCredit){
        var sale = new Sale();
        sale.setTotalSale(saleRequest.totalSale());
        sale.setCustomer(customer);
        sale.setUser(user);
        sale.setStatus(isCredit ? SaleStatus.PENDING : SaleStatus.PAID);
        return sale;
    }

    @Override
    public SaleResponse toDto(Sale sale){
        return new SaleResponse(
                sale.getId(),
                sale.getDate(),
                sale.getTotalSale(),
                sale.getStatus(),
                sale.getCustomer().getId(),
                sale.getCustomer().getName(),
                sale.getUser().getId(),
                sale.getUser().getFullName()
        );
    }
}

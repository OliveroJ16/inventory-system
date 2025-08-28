package com.inventory.inventorySystem.mapper;

import com.inventory.inventorySystem.dto.response.SaleDetailResponse;
import com.inventory.inventorySystem.dto.response.CompleteSaleResponse;
import com.inventory.inventorySystem.dto.response.SaleResponse;
import com.inventory.inventorySystem.mapper.interfaces.SaleMapper;
import com.inventory.inventorySystem.model.Customer;
import com.inventory.inventorySystem.model.Sale;
import com.inventory.inventorySystem.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class SaleMapperImpl implements SaleMapper {

    @Override
    public Sale toEntity(User user, Customer customer){
        var sale = new Sale();
        sale.setCustomer(customer);
        sale.setUser(user);
        return sale;
    }

    @Override
    public CompleteSaleResponse toDto(Sale sale, List<SaleDetailResponse> details) {
        UUID customerId = null;
        String customerName = null;

        if (sale.getCustomer() != null) {
            customerId = sale.getCustomer().getId();
            customerName = sale.getCustomer().getName();
        }

        return new CompleteSaleResponse(
                sale.getId(),
                sale.getDate(),
                sale.getTotalSale(),
                sale.getStatus(),
                customerId,
                customerName,
                sale.getUser().getId(),
                sale.getUser().getFullName(),
                details
        );
    }

    @Override
    public SaleResponse toDto(Sale sale) {
        UUID customerId = null;
        String customerName = null;
        if (sale.getCustomer() != null) {
            customerId = sale.getCustomer().getId();
            customerName = sale.getCustomer().getName();
        }

        UUID userId = null;
        String userName = null;
        if (sale.getUser() != null) {
            userId = sale.getUser().getId();
            userName = sale.getUser().getFullName();
        }

        return new SaleResponse(
                sale.getId(),
                sale.getDate(),
                sale.getTotalSale(),
                sale.getStatus(),
                customerId,
                customerName,
                userId,
                userName
        );
    }

}

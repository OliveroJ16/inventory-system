package com.inventory.inventorySystem.mapper;

import com.inventory.inventorySystem.dto.request.CustomerRequest;
import com.inventory.inventorySystem.dto.response.CustomerResponse;
import com.inventory.inventorySystem.mapper.interfaces.CustomerMapper;
import com.inventory.inventorySystem.model.Customer;
import com.inventory.inventorySystem.utils.StringNormalizer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerMapperImpl implements CustomerMapper {

    private final StringNormalizer stringNormalizer;

    @Override
    public Customer toEntity(CustomerRequest request) {
        if (request == null) {
            return null;
        }
        var customer = new Customer();
        customer.setName(stringNormalizer.toTitleCase(request.name()));
        customer.setLastName(stringNormalizer.toTitleCase(request.lastName()));
        customer.setPhone(request.phone());
        customer.setEmail(request.email());
        customer.setAddress(request.address());
        return customer;
    }

    @Override
    public CustomerResponse toDto(Customer customer) {
        if (customer == null) {
            return null;
        }
        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getLastName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getRegistrationDate()
        );
    }

    @Override
    public void applyPartialUpdate(Customer customer, CustomerRequest request) {
        if (request == null || customer == null) {
            return;
        }

        if (request.name() != null) {
            customer.setName(stringNormalizer.toTitleCase(request.name()));
        }
        if (request.lastName() != null) {
            customer.setLastName(stringNormalizer.toTitleCase(request.lastName()));
        }
        if (request.phone() != null) {
            customer.setPhone(request.phone());
        }
        if (request.email() != null) {
            customer.setEmail(request.email());
        }
        if (request.address() != null) {
            customer.setAddress(request.address());
        }
    }

}

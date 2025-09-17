package com.inventory.inventorySystem.mapper;

import com.inventory.inventorySystem.dto.request.SupplierRequest;
import com.inventory.inventorySystem.dto.response.SupplierResponse;
import com.inventory.inventorySystem.mapper.interfaces.SupplierMapper;
import com.inventory.inventorySystem.model.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapperImpl implements SupplierMapper {

    @Override
    public Supplier toEntity(SupplierRequest request) {
        Supplier supplier = new Supplier();
        supplier.setFullName(request.fullName());
        supplier.setPhone(request.phone());
        supplier.setEmail(request.email());
        supplier.setAddress(request.address());
        supplier.setStatus(request.status());
        return supplier;
    }

    @Override
    public SupplierResponse toDto(Supplier supplier) {
        return new SupplierResponse(
                supplier.getId(),
                supplier.getFullName(),
                supplier.getPhone(),
                supplier.getEmail(),
                supplier.getAddress(),
                supplier.getRegistrationDate(),
                supplier.getStatus()
        );
    }

    @Override
    public void applyPartialUpdate(Supplier supplier, SupplierRequest request) {
        if (request.fullName() != null) {
            supplier.setFullName(request.fullName());
        }
        if (request.phone() != null) {
            supplier.setPhone(request.phone());
        }
        if (request.email() != null) {
            supplier.setEmail(request.email());
        }
        if (request.address() != null) {
            supplier.setAddress(request.address());
        }
        if (request.status() != null) {
            supplier.setStatus(request.status());
        }
    }
}

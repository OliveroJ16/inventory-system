package com.inventory.inventorySystem.mapper.interfaces;

import com.inventory.inventorySystem.dto.request.SupplierRequest;
import com.inventory.inventorySystem.dto.response.SupplierResponse;
import com.inventory.inventorySystem.model.Supplier;

public interface SupplierMapper {
    Supplier toEntity(SupplierRequest supplierRequest);
    SupplierResponse toDto(Supplier supplier);
    void applyPartialUpdate(Supplier supplier, SupplierRequest supplierRequest);
}

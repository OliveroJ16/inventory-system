package com.inventory.inventorySystem.service.interfaces;

import com.inventory.inventorySystem.dto.request.SupplierRequest;
import com.inventory.inventorySystem.dto.response.SupplierResponse;

import java.util.UUID;

public interface SupplierService {
    SupplierResponse saveSupplier(SupplierRequest supplierRequest);
    SupplierResponse updateSupplier(UUID id, SupplierRequest supplierRequest);
}

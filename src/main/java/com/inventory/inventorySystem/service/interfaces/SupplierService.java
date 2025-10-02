package com.inventory.inventorySystem.service.interfaces;

import com.inventory.inventorySystem.dto.request.SupplierRequest;
import com.inventory.inventorySystem.dto.response.SupplierResponse;

public interface SupplierService {
    SupplierResponse saveSupplier(SupplierRequest supplierRequest);
}

package com.inventory.inventorySystem.service.interfaces;

import com.inventory.inventorySystem.dto.request.SupplierRequest;
import com.inventory.inventorySystem.dto.response.PaginatedResponse;
import com.inventory.inventorySystem.dto.response.SupplierResponse;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface SupplierService {
    SupplierResponse saveSupplier(SupplierRequest supplierRequest);
    SupplierResponse updateSupplier(UUID id, SupplierRequest supplierRequest);
    PaginatedResponse<SupplierResponse> getAllSupplier(Pageable pageable);
}

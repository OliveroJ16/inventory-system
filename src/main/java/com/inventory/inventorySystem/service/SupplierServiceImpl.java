package com.inventory.inventorySystem.service;

import com.inventory.inventorySystem.dto.request.SupplierRequest;
import com.inventory.inventorySystem.dto.response.SupplierResponse;
import com.inventory.inventorySystem.mapper.interfaces.SupplierMapper;
import com.inventory.inventorySystem.model.Supplier;
import com.inventory.inventorySystem.repository.SupplierRepository;
import com.inventory.inventorySystem.service.interfaces.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    @Override
    public SupplierResponse saveSupplier(SupplierRequest supplierRequest){
        Supplier supplier = supplierMapper.toEntity(supplierRequest);
        Supplier savedSupplier = supplierRepository.save(supplier);
        return supplierMapper.toDto(savedSupplier);
    }


}

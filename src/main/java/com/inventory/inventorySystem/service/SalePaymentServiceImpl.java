package com.inventory.inventorySystem.service;

import com.inventory.inventorySystem.dto.request.SalePaymentRequest;
import com.inventory.inventorySystem.dto.response.SalePaymentResponse;
import com.inventory.inventorySystem.mapper.interfaces.SalePaymentMapper;
import com.inventory.inventorySystem.model.Sale;
import com.inventory.inventorySystem.model.SalePayment;
import com.inventory.inventorySystem.repository.SalePaymentRepository;
import com.inventory.inventorySystem.service.interfaces.SalePaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalePaymentServiceImpl implements SalePaymentService {

    private final SalePaymentRepository salePaymentRepository;
    private final SalePaymentMapper salePaymentMapper;

    @Override
    public SalePaymentResponse saveSalePayment(SalePaymentRequest salePaymentRequest, Sale sale){
        SalePayment salePayment = salePaymentMapper.toEntity(salePaymentRequest, sale);
        salePayment = salePaymentRepository.save(salePayment);
        return salePaymentMapper.toDto(salePayment);
    }


}

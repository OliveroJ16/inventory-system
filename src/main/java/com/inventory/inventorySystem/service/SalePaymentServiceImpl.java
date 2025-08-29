package com.inventory.inventorySystem.service;

import com.inventory.inventorySystem.dto.request.SalePaymentRequest;
import com.inventory.inventorySystem.dto.response.SalePaymentResponse;
import com.inventory.inventorySystem.enums.SaleStatus;
import com.inventory.inventorySystem.exceptions.ResourceNotFoundException;
import com.inventory.inventorySystem.mapper.interfaces.SalePaymentMapper;
import com.inventory.inventorySystem.model.Sale;
import com.inventory.inventorySystem.model.SalePayment;
import com.inventory.inventorySystem.repository.SalePaymentRepository;
import com.inventory.inventorySystem.repository.SaleRepository;
import com.inventory.inventorySystem.service.interfaces.SalePaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SalePaymentServiceImpl implements SalePaymentService {

    private final SalePaymentRepository salePaymentRepository;
    private final SalePaymentMapper salePaymentMapper;
    private final SaleRepository saleRepository;

    @Override
    public SalePaymentResponse saveSalePayment(SalePaymentRequest salePaymentRequest, Sale sale){
        SalePayment salePayment = salePaymentMapper.toEntity(salePaymentRequest, sale);
        salePayment = salePaymentRepository.save(salePayment);
        return salePaymentMapper.toDto(salePayment);
    }

    @Override
    public SalePaymentResponse updateSalePayment(SalePaymentRequest salePaymentRequest, UUID id){
        SalePayment salePayment = salePaymentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("SalePayment", "id", id));

        Sale sale = saleRepository.findById(salePayment.getSale().getId()).orElseThrow(
                () -> new ResourceNotFoundException("Sale", "id", id));

        BigDecimal totalSale = sale.getTotalSale();
        BigDecimal amountTotal = salePayment.getAmountPaid();
        BigDecimal total =  amountTotal.add(salePaymentRequest.amountPaid());
        if(total.compareTo(totalSale) >= 0){
            sale.setStatus(SaleStatus.PAID);
            saleRepository.save(sale);
        }
        salePayment.setAmountPaid(total);
        salePaymentRepository.save(salePayment);
        return salePaymentMapper.toDto(salePayment);
    }
}

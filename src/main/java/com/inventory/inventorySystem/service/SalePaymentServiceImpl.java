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
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SalePaymentServiceImpl implements SalePaymentService {

    private final SalePaymentRepository salePaymentRepository;
    private final SalePaymentMapper salePaymentMapper;
    private final SaleRepository saleRepository;

    @Override
    @Transactional
    public SalePaymentResponse saveSalePayment(SalePaymentRequest salePaymentRequest, UUID idSale) {
        Sale sale = saleRepository.findById(idSale)
                .orElseThrow(() -> new ResourceNotFoundException("Sale", "id", idSale));

        if (sale.getStatus() == SaleStatus.PAID) {
            throw new IllegalStateException("La venta ya ha sido pagada en su totalidad.");
            // Handler exception last
        }

        SalePayment salePayment = salePaymentMapper.toEntity(salePaymentRequest, sale);
        salePayment = salePaymentRepository.saveAndFlush(salePayment);

        BigDecimal totalPaidSoFar = getSaleTotalPaid(sale);
        BigDecimal newTotalPaid = totalPaidSoFar.add(salePayment.getAmountPaid());

        if (newTotalPaid.compareTo(sale.getTotalSale()) > 0) {
            throw new IllegalArgumentException("El monto total pagado no puede ser mayor que el total de la venta.");
            // Handler exception last
        }

        if (newTotalPaid.compareTo(sale.getTotalSale()) >= 0) {
            sale.setStatus(SaleStatus.PAID);
            saleRepository.save(sale);
        }
        return salePaymentMapper.toDto(salePayment);
    }


    public BigDecimal getSaleTotalPaid(Sale sale) {
        List<SalePayment> payments = salePaymentRepository.findBySaleId(sale.getId());
        return payments.stream()
                .map(SalePayment::getAmountPaid)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

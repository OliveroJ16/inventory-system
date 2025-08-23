package com.inventory.inventorySystem.service;

import com.inventory.inventorySystem.dto.request.SaleRequest;
import com.inventory.inventorySystem.dto.response.SaleDetailResponse;
import com.inventory.inventorySystem.dto.response.SalePaymentResponse;
import com.inventory.inventorySystem.dto.response.SaleResponse;
import com.inventory.inventorySystem.enums.SaleStatus;
import com.inventory.inventorySystem.exceptions.ResourceNotFoundException;
import com.inventory.inventorySystem.mapper.interfaces.SaleMapper;
import com.inventory.inventorySystem.model.Customer;
import com.inventory.inventorySystem.model.Sale;
import com.inventory.inventorySystem.model.User;
import com.inventory.inventorySystem.repository.CustomerRepository;
import com.inventory.inventorySystem.repository.SaleRepository;
import com.inventory.inventorySystem.repository.UserRepository;
import com.inventory.inventorySystem.service.interfaces.SaleDetailService;
import com.inventory.inventorySystem.service.interfaces.SalePaymentService;
import com.inventory.inventorySystem.service.interfaces.SaleService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final SaleDetailService saleDetailService;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final SaleMapper saleMapper;
    private final SalePaymentService salePaymentService;

    @Override
    @Transactional
    public SaleResponse registerSale(SaleRequest saleRequest) {
        User user = userRepository.findById(saleRequest.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", saleRequest.userId()));

        Customer customer = verifyCustomer(saleRequest.customerId());

        Sale sale = saleMapper.toEntity(user, customer);
        sale.setTotalSale(BigDecimal.ZERO);
        sale.setStatus(SaleStatus.PENDING);
        sale = saleRepository.saveAndFlush(sale);

        List<SaleDetailResponse> saleDetailResponses = saleDetailService.registerSaleDetail(saleRequest.details(), sale);
        BigDecimal totalSale = calculateTotalAmount(saleDetailResponses);
        sale.setTotalSale(totalSale);

        processSalePayment(saleRequest, sale);

        sale = saleRepository.save(sale);

        return saleMapper.toDto(sale, saleDetailResponses);
    }

    private BigDecimal calculateTotalAmount(List<SaleDetailResponse> saleDetailResponses){
        return saleDetailResponses.stream()
                .map(SaleDetailResponse::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Customer can be null → indicates that the sale does not have an associated customer.
     */
    private Customer verifyCustomer(UUID customerId){
        Customer customer = null;
        if(customerId != null){
            customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
        }
        return customer;
    }

    private void processSalePayment(SaleRequest saleRequest, Sale sale) {
        BigDecimal totalSale = sale.getTotalSale();
        BigDecimal amountPaid = saleRequest.salePayment().amountPaid();

        if (amountPaid.compareTo(totalSale) > 0) {
            throw new IllegalArgumentException("The amount paid cannot be greater than the total sale amount.");
            // Handle this exception later (personalize)
        }

        if (amountPaid.compareTo(BigDecimal.ZERO) > 0) {
            salePaymentService.saveSalePayment(saleRequest.salePayment(), sale);
            sale.setStatus(amountPaid.compareTo(totalSale) == 0 ? SaleStatus.PAID : SaleStatus.PENDING);
        } else {
            sale.setStatus(SaleStatus.PENDING);
        }
    }
}

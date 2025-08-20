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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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

        Customer customer = null;
        if(saleRequest.customerId() != null){
            customer = customerRepository.findById(saleRequest.customerId()).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "id", saleRequest.customerId())
            );
        }

        Sale sale = saleMapper.toEntity(user, customer, customer != null);
        sale.setTotalSale(BigDecimal.ZERO);
        sale = saleRepository.saveAndFlush(sale);

        List<SaleDetailResponse> saleDetailResponses = saleDetailService.registerSaleDetail(saleRequest.details(), sale);
        BigDecimal totalSale = saleDetailResponses.stream()
                .map(SaleDetailResponse::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal amountPaid = saleRequest.salePayment().amountPaid();
        BigDecimal total = sale.getTotalSale();

        if (amountPaid.compareTo(total) > 0) {
            throw new IllegalArgumentException("The amount paid cannot be greater than the total sale amount.");
        }

        if (sale.getStatus().equals(SaleStatus.PAID)) {
            if (amountPaid.compareTo(total) != 0) {
                throw new IllegalArgumentException("For a paid sale, the amount paid must be equal to the total.");
            }
        } else if (sale.getStatus().equals(SaleStatus.PENDING)) {
            if (amountPaid.compareTo(total) >= 0) {
                throw new IllegalArgumentException("For a credit sale, the amount paid cannot be equal to or greater than the total.");
            }
        }

        sale.setTotalSale(totalSale);
        sale = saleRepository.save(sale);

        SalePaymentResponse salePaymentResponse = salePaymentService.saveSalePayment(saleRequest.salePayment(), sale);

        return saleMapper.toDto(sale, salePaymentResponse,saleDetailResponses);
    }
}

package com.inventory.inventorySystem.service;

import com.inventory.inventorySystem.dto.request.SaleRequest;
import com.inventory.inventorySystem.dto.response.SaleDetailResponse;
import com.inventory.inventorySystem.dto.response.SaleResponse;
import com.inventory.inventorySystem.exceptions.ResourceNotFoundException;
import com.inventory.inventorySystem.mapper.interfaces.SaleMapper;
import com.inventory.inventorySystem.model.Customer;
import com.inventory.inventorySystem.model.Sale;
import com.inventory.inventorySystem.model.User;
import com.inventory.inventorySystem.repository.CustomerRepository;
import com.inventory.inventorySystem.repository.SaleRepository;
import com.inventory.inventorySystem.repository.UserRepository;
import com.inventory.inventorySystem.service.interfaces.SaleDetailService;
import com.inventory.inventorySystem.service.interfaces.SaleService;
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

    @Override
    public SaleResponse registerSale(SaleRequest saleRequest) {
        User user = userRepository.findById(saleRequest.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", saleRequest.userId()));

        Customer customer = customerRepository.findById(saleRequest.customerId()).orElse(null);

        Sale sale = saleMapper.toEntity(user, customer, customer != null);
        sale.setTotalSale(BigDecimal.ZERO);
        sale = saleRepository.save(sale);

        List<SaleDetailResponse> saleDetailResponses = saleDetailService.registerSaleDetail(saleRequest.details(), sale);
        BigDecimal totalSale = saleDetailResponses.stream()
                .map(SaleDetailResponse::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        sale.setTotalSale(totalSale);
        sale = saleRepository.save(sale);

        return saleMapper.toDto(sale, saleDetailResponses);
    }
}

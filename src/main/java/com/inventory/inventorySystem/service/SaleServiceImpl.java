package com.inventory.inventorySystem.service;

import com.inventory.inventorySystem.dto.request.SaleRequest;
import com.inventory.inventorySystem.dto.response.SaleResponse;
import com.inventory.inventorySystem.exceptions.ResourceNotFoundException;
import com.inventory.inventorySystem.mapper.interfaces.SaleMapper;
import com.inventory.inventorySystem.model.Customer;
import com.inventory.inventorySystem.model.Sale;
import com.inventory.inventorySystem.model.User;
import com.inventory.inventorySystem.repository.CustomerRepository;
import com.inventory.inventorySystem.repository.SaleRepository;
import com.inventory.inventorySystem.repository.UserRepository;
import com.inventory.inventorySystem.service.interfaces.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final SaleMapper saleMapper;

    @Override
    public SaleResponse registerSale(SaleRequest saleRequest){
        User user = userRepository.findById(saleRequest.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", saleRequest.userId()));
        Customer customer = customerRepository.findById(saleRequest.customerId()).orElse(null);
        Sale sale = saleMapper.toEntity(saleRequest, user, customer, customer != null);
        return saleMapper.toDto(sale);
    }
}

package com.inventory.inventorySystem.service;

import com.inventory.inventorySystem.dto.request.CustomerRequest;
import com.inventory.inventorySystem.dto.response.CustomerResponse;
import com.inventory.inventorySystem.dto.response.PaginatedResponse;
import com.inventory.inventorySystem.exceptions.ResourceNotFoundException;
import com.inventory.inventorySystem.mapper.interfaces.CustomerMapper;
import com.inventory.inventorySystem.model.Customer;
import com.inventory.inventorySystem.repository.CustomerRepository;
import com.inventory.inventorySystem.service.interfaces.CustomerService;
import com.inventory.inventorySystem.utils.StringNormalizer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final StringNormalizer stringNormalizer;

    @Override
    public CustomerResponse registerCustomer(CustomerRequest customerRequest){
        Customer customer = customerMapper.toEntity(customerRequest);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toDto(savedCustomer);
    }

    @Override
    public CustomerResponse updateCustomer(UUID id, CustomerRequest customerRequest){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        customerMapper.applyPartialUpdate(customer, customerRequest);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toDto(savedCustomer);
    }

    @Override
    public PaginatedResponse<CustomerResponse> getAllCustomers(String name, Pageable pageable){
        Page<Customer> customerPage;
        if(name != null && !name.trim().isEmpty()){
            customerPage = customerRepository.findByName(stringNormalizer.toTitleCase(name), pageable);
        }else{
            customerPage = customerRepository.findAll(pageable);
        }
        Page<CustomerResponse> responsePage = customerPage.map(customerMapper::toDto);
        return new PaginatedResponse<>(responsePage);
    }

}

package com.inventory.inventorySystem.repository;

import com.inventory.inventorySystem.model.Sale;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface SaleRepository extends JpaRepository<Sale, UUID> {
    Page<Sale> findByDateBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);
    Page<Sale> findByCustomerName(String customerName, Pageable pageable);
}

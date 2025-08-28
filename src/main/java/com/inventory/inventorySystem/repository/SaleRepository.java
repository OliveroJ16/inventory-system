package com.inventory.inventorySystem.repository;

import com.inventory.inventorySystem.model.Sale;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Repository
public interface SaleRepository extends JpaRepository<Sale, UUID> {
    Page<Sale> findByDateBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);
}

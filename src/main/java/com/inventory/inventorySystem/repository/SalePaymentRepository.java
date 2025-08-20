package com.inventory.inventorySystem.repository;

import com.inventory.inventorySystem.model.SalePayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SalePaymentRepository extends JpaRepository<SalePayment, UUID> {
}

package com.inventory.inventorySystem.repository;

import com.inventory.inventorySystem.model.Sale;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, UUID> {
}

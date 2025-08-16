package com.inventory.inventorySystem.repository;

import com.inventory.inventorySystem.model.SaleDetail;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, UUID> {
}

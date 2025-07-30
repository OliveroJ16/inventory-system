package com.inventory.inventorySystem.repository;

import com.inventory.inventorySystem.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<Token, UUID> {
    Optional<Token> findByUserId(UUID userId);
    Optional<Token> findByRefreshToken(String refreshToken);
}

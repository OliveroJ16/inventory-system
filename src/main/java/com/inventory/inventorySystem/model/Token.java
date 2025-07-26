package com.inventory.inventorySystem.model;
import com.inventory.inventorySystem.enums.TokenType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tokens")
public class Token {

    @Id
    @UuidGenerator
    @Column(name = "id_token", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "token", nullable = false, columnDefinition = "TEXT")
    private String token;

    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "token_type", nullable = false)
    private TokenType tokenType;

    @Column(name = "expired", nullable = false)
    private Boolean expired;

    @Column(name = "revoked", nullable = false)
    private Boolean revoked;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

}

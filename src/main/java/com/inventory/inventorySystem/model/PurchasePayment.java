package com.inventory.inventorySystem.model;

import com.inventory.inventorySystem.enums.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "purchase_payments")
public class PurchasePayment {

    @Id
    @UuidGenerator
    @Column(name = "id_purchase_payment", nullable = false, columnDefinition = "UUID")
    private UUID id;

    @Column(name = "payment_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime paymentDate;

    @Column(name = "amount_paid", nullable = false, precision = 10, scale = 2)
    private BigDecimal amountPaid;

    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "payment_type", nullable = false, columnDefinition = "payment_type")
    private PaymentType paymentType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_purchase", nullable = false)
    private Purchase purchase;

    public PurchasePayment(BigDecimal amountPaid, PaymentType paymentType, Purchase purchase){
        this.amountPaid = amountPaid;
        this.paymentType = paymentType;
        this.purchase = purchase;
    }
}

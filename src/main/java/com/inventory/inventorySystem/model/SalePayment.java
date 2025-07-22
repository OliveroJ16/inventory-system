package com.inventory.inventorySystem.model;

import com.inventory.inventorySystem.enums.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sale_payments")
public class SalePayment {

    @Id
    @UuidGenerator
    @Column(name = "id_sale_payment", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "payment_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime paymentDate;

    @Column(name = "amount_paid", nullable = false, precision = 10, scale = 2)
    private BigDecimal amountPaid;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", nullable = false)
    private PaymentType paymentType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_sale", nullable = false)
    private Sale sale;

    public SalePayment(BigDecimal amountPaid, PaymentType paymentType, Sale sale){
        this.amountPaid = amountPaid;
        this.paymentType = paymentType;
        this.sale = sale;
    }
}

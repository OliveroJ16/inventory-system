package com.inventory.inventorySystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "purchase_details")
public class PurchaseDetail {

    @Id
    @UuidGenerator
    @Column(name = "id_purchase_detail", nullable = false, columnDefinition = "UUID")
    private UUID id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "subtotal", nullable = false, precision = 10, scale = 2, insertable = false, updatable = false, columnDefinition = "NUMERIC(10,2) GENERATED ALWAYS AS (quantity * unit_price) STORED")
    private BigDecimal subtotal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_purchase", nullable = false)
    private Purchase purchase;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_article", nullable = false)
    private Article article;

    public PurchaseDetail(Integer quantity, BigDecimal subtotal, Purchase purchase, Article article){
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.purchase = purchase;
        this.article = article;
    }
}

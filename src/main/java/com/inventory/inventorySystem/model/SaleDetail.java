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
@Table(name = "sale_details")
public class SaleDetail {

    @Id
    @UuidGenerator
    @Column(name = "id_sale_detail", nullable = false)
    private UUID idSaleDetail;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sale", nullable = false)
    private Sale sale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_article", nullable = false)
    private Article article;

    @Column(name = "subtotal", insertable = false, updatable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

    public SaleDetail(Integer quantity, BigDecimal unitPrice, Sale sale, Article article) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.sale = sale;
        this.article = article;
    }
}

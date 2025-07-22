package com.inventory.inventorySystem.model;

import com.inventory.inventorySystem.enums.SaleStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @Column(name = "id_sale", nullable = false)
    private UUID id;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "total_sale", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalSale;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SaleStatus status;

    @ManyToOne
    @JoinColumn(name = "id_customer", foreignKey = @ForeignKey(name = "fk_sale_customer"))
    private Customer customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "fk_sale_user"))
    private User user;

    public Sale(UUID id, LocalDateTime date, BigDecimal totalSale, SaleStatus status, Customer customer, User user) {
        this.id = id;
        this.date = date;
        this.totalSale = totalSale;
        this.status = status;
        this.customer = customer;
        this.user = user;
    }

}

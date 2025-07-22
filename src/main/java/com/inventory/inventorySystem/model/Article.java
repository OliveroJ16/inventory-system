package com.inventory.inventorySystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
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
@Table(name = "articles")
public class Article{
    @Id
    @UuidGenerator
    @Column(name = "id_article", nullable = false, columnDefinition = "UUID")
    private UUID id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "description")
    private String description;

    @Column(name = "unit_of_measurement", nullable = false, length = 50)
    private String unitOfMeasurement;

    @CreationTimestamp
    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "image_url", length = 300)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_category", nullable = false)
    private Category category;

    @Column(name = "content", nullable = false, precision = 10, scale = 2)
    private BigDecimal content;

    public Article(String name, BigDecimal unitPrice, Integer stock, String description, String unitOfMeasurement, Boolean status, String imageUrl, Category category, BigDecimal content) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.description = description;
        this.unitOfMeasurement = unitOfMeasurement;
        this.status = status;
        this.imageUrl = imageUrl;
        this.category = category;
        this.content = content;
    }
}

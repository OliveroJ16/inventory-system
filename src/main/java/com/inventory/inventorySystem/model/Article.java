package com.inventory.inventorySystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "articles")
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "id_article"))
public class Article{
    @Id
    @Column(name = "id_article", nullable = false, columnDefinition = "UUID")
    private UUID id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "description")
    private String description;

    @Column(name = "unit_of_measurement", nullable = false, length = 50)
    private String unitOfMeasurement;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "image_url", length = 300)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_category", nullable = false, foreignKey = @ForeignKey(name = "fk_article_category"))
    private Category category;

    @Column(name = "content", nullable = false, precision = 10, scale = 2)
    private BigDecimal content;

    public Article(UUID id, String name, BigDecimal unitPrice, int stock, String description, String unitOfMeasurement, LocalDateTime creationDate, boolean status, String imageUrl, Category category, BigDecimal content) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.description = description;
        this.unitOfMeasurement = unitOfMeasurement;
        this.creationDate = creationDate;
        this.status = status;
        this.imageUrl = imageUrl;
        this.category = category;
        this.content = content;
    }
}

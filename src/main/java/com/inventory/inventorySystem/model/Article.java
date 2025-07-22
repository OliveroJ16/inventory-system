package com.inventory.inventorySystem.model;

import com.inventory.inventorySystem.utils.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "articles")
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "id_article"))
public class Article extends BaseEntity {

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "unit_of_measurement", nullable = false, length = 50)
    private String unitOfMeasurement;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "image_url", length = 300)
    private String imageUrl;

    @Column(name = "content", nullable = false, precision = 10, scale = 2)
    private BigDecimal content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_category", nullable = false)
    private Category category;

    public Article(String name, BigDecimal unitPrice, Integer stock, String unitOfMeasurement, BigDecimal content, Category category) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.unitOfMeasurement = unitOfMeasurement;
        this.content = content;
        this.category = category;
        this.status = true;
    }

    public Article(String name, BigDecimal unitPrice, Integer stock, String unitOfMeasurement, String description, BigDecimal content, boolean status, String imageUrl, Category category) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.unitOfMeasurement = unitOfMeasurement;
        this.description = description;
        this.content = content;
        this.status = status;
        this.imageUrl = imageUrl;
        this.category = category;
    }
}

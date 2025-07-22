package com.inventory.inventorySystem.model;

import com.inventory.inventorySystem.utils.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "categories")
@AttributeOverride(name = "id", column = @Column(name = "id_category"))
public class Category extends BaseEntity {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "status", nullable = false)
    private boolean status;

    public Category(String name) {
        this.name = name;
        this.status = true;
    }
}

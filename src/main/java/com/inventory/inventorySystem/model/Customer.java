package com.inventory.inventorySystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    @Column(name = "id_customer", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registrationDate;

    public Customer(UUID id, String name, String lastName, String phone, String email, String address, LocalDateTime registrationDate) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.registrationDate = registrationDate;
    }
}

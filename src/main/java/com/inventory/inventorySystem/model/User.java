package com.inventory.inventorySystem.model;

import com.inventory.inventorySystem.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @UuidGenerator
    @Column(name = "id_user", nullable = false, columnDefinition = "UUID")
    private UUID id;

    @Column(name = "user_name", unique = true, nullable = false, length = 75)
    private String userName;

    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;

    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;

    @Column(name = "password", nullable = false, length = 250)
    private String password;

    @CreationTimestamp
    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "status", nullable = false)
    private Boolean status;

    public User (String userName, String fullName, String email, UserRole role, String password, Boolean status){
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.password =password;
        this.status = status;
    }
}

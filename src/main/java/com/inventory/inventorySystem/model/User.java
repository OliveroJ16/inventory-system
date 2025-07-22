package com.inventory.inventorySystem.model;

import com.inventory.inventorySystem.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @Column(name = "id_user", nullable = false)
    private UUID id;

    @Column(name = "user_name", nullable = false, length = 75)
    private String userName;

    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;

    @Column(name = "password", nullable = false, length = 250)
    private String password;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "status", nullable = false)
    private boolean status;

    public User (UUID id, String userName, String fullName, String email, UserRole role, String password, LocalDateTime creationDate, boolean status){
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.password =password;
        this.creationDate = creationDate;
        this.status = status;
    }
}

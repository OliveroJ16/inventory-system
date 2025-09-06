package com.inventory.inventorySystem.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserRequest(

        @Size(min = 5, max = 50, message = "Username must be between 5 and 50 characters")
        String userName,

        @Size(min = 3, max = 150, message = "Full name must be between 3 and 150 characters")
        String fullName,

        @Email(message = "Email must be a valid format")
        String email,

        Boolean status
) { }

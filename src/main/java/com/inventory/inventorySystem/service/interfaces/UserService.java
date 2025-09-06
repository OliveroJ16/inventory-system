package com.inventory.inventorySystem.service.interfaces;

import com.inventory.inventorySystem.dto.request.RegisterRequest;
import com.inventory.inventorySystem.dto.request.UserRequest;
import com.inventory.inventorySystem.dto.response.UserResponse;

import java.util.UUID;

public interface UserService {
    UserResponse saveUser(RegisterRequest registerRequest);
    UserResponse updateUser(UUID id, UserRequest userRequest);
}

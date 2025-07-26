package com.inventory.inventorySystem.service.interfaces;

import com.inventory.inventorySystem.dto.request.RegisterRequest;
import com.inventory.inventorySystem.dto.response.UserResponse;

public interface UserService {
    UserResponse saveUser(RegisterRequest registerRequest);
}

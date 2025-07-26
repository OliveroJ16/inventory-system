package com.inventory.inventorySystem.service.interfaces;

import com.inventory.inventorySystem.dto.request.RegisterRequest;
import com.inventory.inventorySystem.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest registerRequest);
}

package com.inventory.inventorySystem.service.interfaces;

import com.inventory.inventorySystem.dto.request.RegisterRequest;
import com.inventory.inventorySystem.dto.response.TokenResponse;

public interface AuthService {
    TokenResponse register(RegisterRequest registerRequest);
}

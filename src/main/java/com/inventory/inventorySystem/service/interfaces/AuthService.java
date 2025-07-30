package com.inventory.inventorySystem.service.interfaces;

import com.inventory.inventorySystem.dto.request.LoginRequest;
import com.inventory.inventorySystem.dto.request.RegisterRequest;
import com.inventory.inventorySystem.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse registerUser(RegisterRequest registerRequest);
    AuthResponse loginUser(LoginRequest loginRequest);
    AuthResponse refreshToken(String authHeader);
    void logout(String authHeader);
}

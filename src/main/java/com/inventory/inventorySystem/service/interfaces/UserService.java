package com.inventory.inventorySystem.service.interfaces;

import com.inventory.inventorySystem.dto.request.RegisterRequest;
import com.inventory.inventorySystem.dto.request.UserRequest;
import com.inventory.inventorySystem.dto.response.PaginatedResponse;
import com.inventory.inventorySystem.dto.response.UserResponse;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {
    UserResponse saveUser(RegisterRequest registerRequest);
    UserResponse updateUser(UUID id, UserRequest userRequest);
    PaginatedResponse<UserResponse> getAllUsers(Pageable pageable);
}

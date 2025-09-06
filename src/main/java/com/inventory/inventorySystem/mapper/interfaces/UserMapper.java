package com.inventory.inventorySystem.mapper.interfaces;

import com.inventory.inventorySystem.dto.request.RegisterRequest;
import com.inventory.inventorySystem.dto.request.UserRequest;
import com.inventory.inventorySystem.dto.response.UserResponse;
import com.inventory.inventorySystem.model.User;

public interface UserMapper {
    User toEntity(RegisterRequest registerRequest);
    UserResponse toDto(User user);
    void applyPartialUpdate(User user, UserRequest userRequest);
}

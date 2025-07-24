package com.inventory.inventorySystem.mapper.interfaces;

import com.inventory.inventorySystem.dto.request.UserRequest;
import com.inventory.inventorySystem.dto.response.UserResponse;
import com.inventory.inventorySystem.model.User;

public interface UserMapper {
    User toEntity(UserRequest userRequest);
    UserResponse toDto(User user);
}

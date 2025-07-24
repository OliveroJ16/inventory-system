package com.inventory.inventorySystem.service.interfaces;

import com.inventory.inventorySystem.dto.request.UserRequest;
import com.inventory.inventorySystem.dto.response.UserResponse;

public interface UserService {
    UserResponse registerUser(UserRequest userRequest);
}

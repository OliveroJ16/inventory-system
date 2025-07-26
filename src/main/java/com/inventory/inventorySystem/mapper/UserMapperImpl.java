package com.inventory.inventorySystem.mapper;

import com.inventory.inventorySystem.dto.request.RegisterRequest;
import com.inventory.inventorySystem.dto.response.UserResponse;
import com.inventory.inventorySystem.enums.UserRole;
import com.inventory.inventorySystem.mapper.interfaces.UserMapper;
import com.inventory.inventorySystem.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(RegisterRequest registerRequest) {
        if (registerRequest == null) {
            return null;
        }

        var user = new User();
        user.setUserName(registerRequest.username());
        user.setFullName(registerRequest.fullName());
        user.setEmail(registerRequest.email());
        user.setRole(UserRole.ADMIN);
        user.setStatus(true);
        return user;
    }

    @Override
    public UserResponse toDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserResponse(
                user.getId(),
                user.getUserName(),
                user.getFullName(),
                user.getEmail(),
                user.getRole(),
                user.getCreationDate(),
                user.getStatus()
        );
    }
}


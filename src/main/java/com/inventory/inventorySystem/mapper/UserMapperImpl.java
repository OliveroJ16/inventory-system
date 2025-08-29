package com.inventory.inventorySystem.mapper;

import com.inventory.inventorySystem.dto.request.RegisterRequest;
import com.inventory.inventorySystem.dto.response.UserResponse;
import com.inventory.inventorySystem.enums.UserRole;
import com.inventory.inventorySystem.mapper.interfaces.UserMapper;
import com.inventory.inventorySystem.model.User;
import com.inventory.inventorySystem.utils.StringNormalizer;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(RegisterRequest registerRequest) {
        var user = new User();
        user.setUserName(StringNormalizer.toTitleCase(registerRequest.username()));
        user.setFullName(StringNormalizer.toTitleCase(registerRequest.fullName()));
        user.setEmail(registerRequest.email());
        user.setRole(UserRole.CASHIER);
        user.setStatus(true);
        return user;
    }

    @Override
    public UserResponse toDto(User user) {
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


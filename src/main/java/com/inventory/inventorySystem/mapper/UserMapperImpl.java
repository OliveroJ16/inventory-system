package com.inventory.inventorySystem.mapper;

import com.inventory.inventorySystem.dto.request.RegisterRequest;
import com.inventory.inventorySystem.dto.request.UserRequest;
import com.inventory.inventorySystem.dto.response.UserResponse;
import com.inventory.inventorySystem.enums.UserRole;
import com.inventory.inventorySystem.mapper.interfaces.UserMapper;
import com.inventory.inventorySystem.model.User;
import com.inventory.inventorySystem.utils.StringNormalizer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    private final StringNormalizer stringNormalizer;

    @Override
    public User toEntity(RegisterRequest registerRequest) {
        var user = new User();
        user.setUserName(registerRequest.username());
        user.setFullName(stringNormalizer.toTitleCase(registerRequest.fullName()));
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

    @Override
    public void applyPartialUpdate(User user, UserRequest userRequest) {
        if (userRequest.userName() != null && !userRequest.userName().isBlank()) {
            user.setUserName(userRequest.userName());
        }

        if (userRequest.fullName() != null && !userRequest.fullName().isBlank()) {
            user.setFullName(stringNormalizer.toTitleCase(userRequest.fullName()));
        }

        if (userRequest.email() != null && !userRequest.email().isBlank()) {
            user.setEmail(userRequest.email());
        }

        if (userRequest.status() != null) {
            user.setStatus(userRequest.status());
        }
    }
}


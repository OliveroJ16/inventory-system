package com.inventory.inventorySystem.services;

import com.inventory.inventorySystem.dto.request.RegisterRequest;
import com.inventory.inventorySystem.dto.response.AuthResponse;
import com.inventory.inventorySystem.dto.response.UserResponse;
import com.inventory.inventorySystem.enums.UserRole;
import com.inventory.inventorySystem.model.Token;
import com.inventory.inventorySystem.repository.TokenRepository;
import com.inventory.inventorySystem.service.AuthServiceImpl;
import com.inventory.inventorySystem.security.JwtTokenProvider;
import com.inventory.inventorySystem.service.interfaces.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private UserService userService;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private TokenRepository tokenRepository;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    void register_ShouldReturnTokenResponse() {
        // Arrange
        RegisterRequest request = new RegisterRequest("jdoe", "Jhon Doe", "jdoe@example.com", "123");
        UserResponse userResponse = new UserResponse(
                UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479"),
                "jdoe",
                "John Doe",
                "jdoe@example.com",
                UserRole.ADMIN,
                LocalDateTime.of(2024, 7, 25, 14, 30),
                true
        );

        when(userService.saveUser(request)).thenReturn(userResponse);
        when(jwtTokenProvider.generateAccessToken(userResponse)).thenReturn("jwt-token");
        when(jwtTokenProvider.generateRefreshToken(userResponse)).thenReturn("refresh-token");

        // Act
        AuthResponse result = authService.registerUser(request);

        // Assert
        assertEquals("jwt-token", result.accessToken());
        assertEquals("refresh-token", result.refreshToken());
        verify(tokenRepository).save(any(Token.class));
    }
}

package com.inventory.inventorySystem.service;

import com.inventory.inventorySystem.dto.request.RegisterRequest;
import com.inventory.inventorySystem.dto.response.AuthResponse;
import com.inventory.inventorySystem.dto.response.UserResponse;
import com.inventory.inventorySystem.enums.TokenType;
import com.inventory.inventorySystem.model.Token;
import com.inventory.inventorySystem.model.User;
import com.inventory.inventorySystem.repository.TokenRepository;
import com.inventory.inventorySystem.repository.UserRepository;
import com.inventory.inventorySystem.security.JwtTokenProvider;
import com.inventory.inventorySystem.service.interfaces.AuthService;
import com.inventory.inventorySystem.service.interfaces.UserService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    public AuthServiceImpl(UserService userService, JwtTokenProvider jwtTokenProvider, TokenRepository tokenRepository, UserRepository userRepository){
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    public AuthResponse register(RegisterRequest registerRequest){
        var userResponse = userService.saveUser(registerRequest);
        var accessToken = jwtTokenProvider.generateToken(userResponse);
        var refreshToken = jwtTokenProvider.generateRefreshToken(userResponse);
        saveToken(userResponse, refreshToken);
        return new AuthResponse(accessToken, refreshToken, userResponse);
    }

    public void saveToken(UserResponse userResponse, String refreshToken){
        User user = userRepository.findById(userResponse.id())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userResponse.id()));
        var token = Token
                .builder()
                .token(refreshToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .user(user).build();
        tokenRepository.save(token);
    }
}

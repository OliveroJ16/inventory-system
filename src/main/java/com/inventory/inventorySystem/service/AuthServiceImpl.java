package com.inventory.inventorySystem.service;

import com.inventory.inventorySystem.dto.request.RegisterRequest;
import com.inventory.inventorySystem.dto.response.TokenResponse;
import com.inventory.inventorySystem.dto.response.UserResponse;
import com.inventory.inventorySystem.model.Token;
import com.inventory.inventorySystem.model.User;
import com.inventory.inventorySystem.repository.TokenRepository;
import com.inventory.inventorySystem.service.interfaces.AuthService;
import com.inventory.inventorySystem.service.interfaces.UserService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;

    public AuthServiceImpl(UserService userService, JwtService jwtService, TokenRepository tokenRepository){
        this.userService = userService;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
    }

    public TokenResponse register(RegisterRequest registerRequest){
        var userResponse = userService.saveUser(registerRequest);
        var jwtToken = jwtService.generateToken(userResponse);
        var refreshToken = jwtService.generateRefreshToken(userResponse);
        saveToken(userResponse, jwtToken);
        return new TokenResponse(jwtToken, refreshToken);
    }

    public void saveToken(UserResponse userResponse, String jwtToken){
        var user = new User();
        user.setId(userResponse.id());
        var token = new Token(jwtToken, user);
        tokenRepository.save(token);
    }
}

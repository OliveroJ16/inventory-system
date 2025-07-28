package com.inventory.inventorySystem.service;

import com.inventory.inventorySystem.dto.request.LoginRequest;
import com.inventory.inventorySystem.dto.request.RegisterRequest;
import com.inventory.inventorySystem.dto.response.AuthResponse;
import com.inventory.inventorySystem.dto.response.UserResponse;
import com.inventory.inventorySystem.enums.TokenType;
import com.inventory.inventorySystem.mapper.interfaces.UserMapper;
import com.inventory.inventorySystem.model.Token;
import com.inventory.inventorySystem.model.User;
import com.inventory.inventorySystem.repository.TokenRepository;
import com.inventory.inventorySystem.repository.UserRepository;
import com.inventory.inventorySystem.security.JwtTokenProvider;
import com.inventory.inventorySystem.service.interfaces.AuthService;
import com.inventory.inventorySystem.service.interfaces.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public AuthServiceImpl(UserService userService, JwtTokenProvider jwtTokenProvider, TokenRepository tokenRepository, UserRepository userRepository, AuthenticationManager authenticationManager, UserMapper userMapper){
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
    }

    @Override
    public AuthResponse registerUser(RegisterRequest registerRequest){
        var userResponse = userService.saveUser(registerRequest);
        var accessToken = jwtTokenProvider.generateToken(userResponse);
        var refreshToken = jwtTokenProvider.generateRefreshToken(userResponse);
        saveToken(userResponse, refreshToken);
        return new AuthResponse(accessToken, refreshToken, userResponse);
    }

    @Override
    public AuthResponse loginUser(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        var user = userRepository.findByEmail(request.email()).orElseThrow();
        var userResponse = userMapper.toDto(user);
        var accessToken = jwtTokenProvider.generateToken(userResponse);
        var refreshToken = jwtTokenProvider.generateRefreshToken(userResponse);
        revokeAllUserTokens(user);
        return new AuthResponse(accessToken, refreshToken, userResponse);
    }

    private void saveToken(UserResponse userResponse, String refreshToken){
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

    private void revokeAllUserTokens(final User user){
        final List<Token> validUserTokens = tokenRepository.findAllByUserIdAndExpiredIsFalseAndRevokedIsFalse(user.getId());
        if(!validUserTokens.isEmpty()){
            for(final Token token : validUserTokens){
                token.setExpired(true);
                token.setRevoked(true);
            }
        }
        tokenRepository.saveAll(validUserTokens);
    }
}

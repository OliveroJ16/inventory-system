package com.inventory.inventorySystem.controller;

import com.inventory.inventorySystem.dto.request.LoginRequest;
import com.inventory.inventorySystem.dto.request.RegisterRequest;
import com.inventory.inventorySystem.dto.response.UserResponse;
import com.inventory.inventorySystem.exceptions.InvalidTokenException;
import com.inventory.inventorySystem.security.CookieProvider;
import com.inventory.inventorySystem.service.interfaces.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final CookieProvider cookieProvider;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody @Valid RegisterRequest registerRequest) {
        var authResponse = authService.registerUser(registerRequest);
        ResponseCookie refreshTokenCookie = cookieProvider.createRefreshTokenCookie(authResponse.refreshToken());
        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + authResponse.accessToken())
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .body(authResponse.user());
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@RequestBody @Valid LoginRequest loginRequest){
        var authResponse = authService.loginUser(loginRequest);
        ResponseCookie refreshTokenCookie = cookieProvider.createRefreshTokenCookie(authResponse.refreshToken());
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + authResponse.accessToken())
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .body(authResponse.user());
    }

    @PostMapping("/refresh")
    public ResponseEntity<UserResponse> refreshToken(HttpServletRequest request) {
        String refreshToken = cookieProvider.getRefreshTokenFromCookie(request)
                .orElseThrow(() -> new InvalidTokenException("Refresh token is missing or invalid"));
        var authResponse = authService.refreshToken(refreshToken);
        ResponseCookie refreshTokenCookie = cookieProvider.createRefreshTokenCookie(authResponse.refreshToken());
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + authResponse.accessToken())
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .body(authResponse.user());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logoutUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        authService.logout(authHeader);
        ResponseCookie deletedCookie = cookieProvider.getDeletedRefreshTokenCookie();
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .header(HttpHeaders.SET_COOKIE, deletedCookie.toString())
                .build();
    }
}

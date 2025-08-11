package com.inventory.inventorySystem.security;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
public class CookieProvider {

    @Value("${jwt.refresh.cookie.name}")
    private String cookieName;

    @Value("${jwt.refresh.cookie.max-age}")
    private int cookieMaxAge;

    public ResponseCookie createRefreshTokenCookie(String refreshToken) {
        return ResponseCookie.from(cookieName, refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(cookieMaxAge)
                .build();
    }

    public Optional<String> getRefreshTokenFromCookie(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return Optional.empty();
        }

        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookieName.equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst();
    }

    public ResponseCookie getDeletedRefreshTokenCookie() {
        return ResponseCookie.from("refresh-token", "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0)
                .build();
    }
}

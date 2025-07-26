package com.inventory.inventorySystem.security;

import com.inventory.inventorySystem.dto.response.UserResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Value("${jwt.refresh-expiration}")
    private long refreshExpiration;

    public String generateToken(final UserResponse user){
        return buildToken(user, jwtExpiration);
    }

    public String generateRefreshToken(final UserResponse user){
        return buildToken(user, refreshExpiration);
    }

    public String buildToken(UserResponse user, final long expiration){
        return Jwts.builder()
                .id(user.id().toString())
                .claims(Map.of("name", user.fullName()))
                .subject(user.email())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey())
                .compact();
    }

    public SecretKey getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

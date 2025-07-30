package com.inventory.inventorySystem.security;

import com.inventory.inventorySystem.dto.response.UserResponse;
import com.inventory.inventorySystem.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Value("${jwt.refresh-expiration}")
    private long refreshExpiration;

    public String generateAccessToken(final UserResponse user){
        return buildToken(user, jwtExpiration);
    }

    public String generateRefreshToken(final UserResponse user){
        return buildToken(user, refreshExpiration);
    }

    private String buildToken(UserResponse user, final long expiration){
        var claims = new HashMap<String, Object>();
        claims.put("userId", user.id());
        claims.put("name", user.fullName());
        claims.put("email", user.email());
        claims.put("role", user.role().name());
        return Jwts.builder()
                .claims(claims)
                .subject(user.email())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey())
                .compact();
    }

    private SecretKey getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsernameFromToken(String token) {
        Claims claims = extractClaimsFromToken(token);
        return claims.getSubject();
    }

    public boolean isTokenValid(final String token, final User user){
        String userEmail = getUsernameFromToken(token);
        return (userEmail.equals(user.getEmail()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        Claims claims = extractClaimsFromToken(token);
        return claims.getExpiration();
    }

    private Claims extractClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}

package com.inventory.inventorySystem.security;

import com.inventory.inventorySystem.dto.response.UserResponse;
import com.inventory.inventorySystem.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Value("${jwt.refresh-expiration}")
    private long refreshExpiration;

    public String generateAccessToken(final UserResponse user) {
        return buildToken(user, jwtExpiration, "access");
    }

    public String generateRefreshToken(final UserResponse user) {
        return buildToken(user, refreshExpiration, "refresh");
    }

    private String buildToken(UserResponse user, long expiration, String typeToken) {
        Map<String, Object> claims = Map.of(
                "userId", user.id(),
                "name", user.fullName(),
                "email", user.email(),
                "role", user.role().name(),
                "type_Token", typeToken
        );

        long now = System.currentTimeMillis();
        return Jwts.builder()
                .claims(claims)
                .subject(user.email())
                .issuedAt(new Date(now))
                .expiration(new Date(now + expiration))
                .signWith(getSignInKey())
                .compact();
    }

    public boolean isAccessTokenValid(String token, User user) {
        try {
            Claims claims = extractClaimsFromToken(token);
            if (!"access".equals(claims.get("type_Token", String.class))) {
                return false;
            }
            return claims.getSubject().equals(user.getEmail()) &&
                    !claims.getExpiration().before(new Date());

        } catch (ExpiredJwtException | SignatureException | MalformedJwtException |
                 UnsupportedJwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public boolean isRefreshTokenValid(String token, User user) {
        try {
            Claims claims = extractClaimsFromToken(token);

            String type = claims.get("type_Token", String.class);
            if (!"refresh".equals(type)) {
                return false;
            }

            boolean notExpired = !claims.getExpiration().before(new Date());
            boolean userMatches = claims.getSubject().equals(user.getEmail());

            return notExpired && userMatches;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return extractClaimsFromToken(token).getSubject();
    }

    private Claims extractClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

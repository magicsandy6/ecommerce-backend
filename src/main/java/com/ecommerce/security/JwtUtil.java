package com.ecommerce.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    // ✅ FIX 4: No hardcoded fallback secret
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration:86400000}")
    private long jwtExpiration;

    // ✅ FIX: Return SecretKey type directly
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateToken(String email) {
        return Jwts.builder()
                // ✅ FIX 3: New method names (no "set" prefix)
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                // ✅ FIX 1: No SignatureAlgorithm needed
                .signWith(getSigningKey())
                .compact();
    }

    public String extractEmail(String token) {
        // ✅ FIX 2: New parser API
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            // ✅ FIX 5: Log the actual error
            log.error("JWT validation failed: {}", e.getMessage());
            return false;
        }
    }
}
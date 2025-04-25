package com.cg.security;


import io.jsonwebtoken.*;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWtUtil {

	private String secretKey = "mySecretKey"; // Change this for production!

    // Generate JWT Token
    public String generateToken(Authentication authentication) {
        long expirationTime = 1000 * 60 * 60 * 10; // 10 hours

        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // Validate JWT Token
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Get username from JWT Token
    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}

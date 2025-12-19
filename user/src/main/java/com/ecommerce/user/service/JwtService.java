package com.ecommerce.user.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET = "CHANGE_ME_TO_A_LONG_RANDOM_SECRET_KEY_32_CHARS_MINIMUM";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    // 1 hour
    private static final long ACCESS_TOKEN_SECONDS = 36000;

    public String generateAccessToken(Long userId, String email, String role) {
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(ACCESS_TOKEN_SECONDS);

        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .claim("email", email)
                .claim("role", role)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(exp))
                .signWith(key)
                .compact();

    }
}

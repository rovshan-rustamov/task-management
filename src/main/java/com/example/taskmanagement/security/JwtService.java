package com.example.taskmanagement.security;

import com.example.taskmanagement.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {
    private final String AUTHORITY = "authority";
    private final String USERNAME = "username";
    private final String EMAIL = "email";
    private final String ORGANIZATION = "organization";


    @Value("${spring.security.secret}")
    private String secret;
    @Value("${security.token.expirationTime}")
    private Long expTimeInMinutes;
    private Key key;

    @PostConstruct
    public void init() {
        byte[] keyBytes;
        keyBytes = Decoders.BASE64.decode(secret);
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String issueTokenForUser(User user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(Date.from(Instant.now().plus(Duration.ofMinutes(expTimeInMinutes))))
                .setHeader(Map.of("type", "JWT"))
                .signWith(key)
                .claim(EMAIL, user.getEmail())
                .claim(USERNAME, user.getUsername())
                .claim("name", user.getName())
                .claim("surname", user.getSurname())
                .claim("phoneNumber", user.getPhoneNumbers())
                .claim(ORGANIZATION, user.getOrganization())
                .claim(AUTHORITY, user.getAuthorities())
                .compact();
    }

}

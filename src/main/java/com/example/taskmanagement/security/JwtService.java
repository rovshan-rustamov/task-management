package com.example.taskmanagement.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${spring.security.secret}")
    private String secret;
    @Value("${security.token.expirationTime}")
    private Long expTimeInMinutes;
    private Key key;

    public String generateToken(UserDetails userDetails) {
        return Jwts
                .builder()
                .claims()
                .add("authorities", userDetails.getAuthorities())
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expTimeInMinutes))
                .and()
                .signWith(getSigningKey())
                .compact();
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .claims()
                .subject(userDetails.getUsername())
                .add(extraClaims)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expTimeInMinutes))
                .and()
                .signWith(getSigningKey())
                .compact();
    }

    public String generateToken(Authentication authentication) {
        return Jwts
                .builder()
                .claims()
                .subject(authentication.getName())
                .add("authorities", authentication.getAuthorities())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expTimeInMinutes))
                .and()
                .signWith(getSigningKey())
                .compact();
    }

//    public String generateRefreshToken(UserDetails userDetails) {
//        return Jwts
//                .builder()
//                .claims()
//                .subject(userDetails.getUsername())
//                .add("authorities", userDetails.getAuthorities())
//                .issuedAt(new Date())
//                .expiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_MS))
//                .and()
//                .signWith(getSigningKey())
//                .compact();
//    }

//    public String generateRefreshToken(Authentication authentication) {
//        return Jwts
//                .builder()
//                .claims()
//                .subject(authentication.getName())
//                .add("authorities", authentication.getAuthorities())
//                .issuedAt(new Date())
//                .expiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_MS))
//                .and()
//                .signWith(getSigningKey())
//                .compact();
//    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSigningKey() {
        byte[] bytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(bytes);
    }

}

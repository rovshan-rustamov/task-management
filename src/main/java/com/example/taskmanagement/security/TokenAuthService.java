package com.example.taskmanagement.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class TokenAuthService {

    private static final String BEARER = "Bearer ";
    private static final String AUTHORITY_CLAIM = "authority";
    private static final String USER_CLAIM = "id";
    private final JwtService jwtService;
    private final ModelMapper modelMapper;

    public Optional<Authentication> getAuthentication(HttpServletRequest req){
        return Optional.ofNullable(req.getHeader("Authorization"))
                .filter(header-> isBearerAuth(header))
                .flatMap(header1 -> getAuthenticationBearer(header1));
    }

    private boolean isBearerAuth(String header) {
        return header.toLowerCase().startsWith(BEARER.toLowerCase());
    }

    private Optional<Authentication> getAuthenticationBearer(String header) {
        var token = header.substring(BEARER.length()).trim();
        var claims = jwtService.parseToken(token);
        log.trace("claims parsed {}", claims);
        if (claims.getExpiration().before(new Date())){
            return Optional.empty();
        }

        return Optional.of(getAuthenticationBearer(claims));
    }

    private Authentication getAuthenticationBearer(Claims claims) {
        List<?> authorities = claims.get(AUTHORITY_CLAIM, List.class);
        List<GrantedAuthority> authorityList = authorities
                .stream()
                .map(authority-> new SimpleGrantedAuthority(authority.toString()))
                .collect(Collectors.toList());
        JwtCredentials credentials = modelMapper.map(claims, JwtCredentials.class);
        User user = new User(credentials.getSub(), "", authorityList);
        return new UsernamePasswordAuthenticationToken(credentials, user,authorityList);
    }

}


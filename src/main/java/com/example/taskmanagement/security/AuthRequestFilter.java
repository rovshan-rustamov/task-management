package com.example.taskmanagement.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthRequestFilter extends OncePerRequestFilter {
    private final TokenAuthService tokenAuthService;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        Optional<Authentication> authentication = tokenAuthService.getAuthentication(httpServletRequest);
        authentication.ifPresent(auth -> {
            SecurityContextHolder.getContext().setAuthentication(auth);
            log.info("AUTHENTICATION IS {}", auth);
        });
        filterChain.doFilter(httpServletRequest, httpServletResponse);
        log.info("working");
    }
}

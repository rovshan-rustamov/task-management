package com.example.taskmanagement.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class AuthFilterConfigurerAdapter extends SecurityConfigurerAdapter <DefaultSecurityFilterChain, HttpSecurity> {

    private final TokenAuthService tokenAuthService;

    @Override
    public void configure(HttpSecurity http){
        http.addFilterBefore(new AuthRequestFilter(tokenAuthService), UsernamePasswordAuthenticationFilter.class);
        log.trace("added auth request filter");
    }
}



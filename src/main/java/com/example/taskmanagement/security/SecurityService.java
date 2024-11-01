package com.example.taskmanagement.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class SecurityService {
    private final ModelMapper modelMapper;
    public JwtCredentials getJwtCredentials(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .map(authentication -> modelMapper.
                        map(authentication.getPrincipal(), JwtCredentials.class))
                .orElseThrow(RuntimeException::new);


    }





}

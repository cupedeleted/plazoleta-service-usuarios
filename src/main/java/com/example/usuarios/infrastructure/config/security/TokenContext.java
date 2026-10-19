package com.example.usuarios.infrastructure.config.security;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class TokenContext{
    private TokenContext(){}
    
    public static Optional<String> actual(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null && auth.getCredentials() instanceof String token){
            return Optional.of(token);
        }
        return Optional.empty();
    }
}
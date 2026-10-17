package com.example.usuarios.infrastructure.config.security;

import com.example.usuarios.domain.spi.ITokenServicePort;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class JwtTokenFilter extends OncePerRequestFilter {

    private final ITokenServicePort tokenServicePort;

    public JwtTokenFilter(ITokenServicePort tokenServicePort) {
        this.tokenServicePort = tokenServicePort;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = getTokenFromRequest(request);
            if (token != null && tokenServicePort.validateToken(token)) {
                String correo = tokenServicePort.getUsernameFromToken(token);
                String rol = tokenServicePort.getRoleFromToken(token); // "ADMINISTRADOR", "PROPIETARIO"
                List<SimpleGrantedAuthority> authorities = Collections.singletonList(
                    new SimpleGrantedAuthority("ROLE_" + rol)
                );
                
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    correo, null, authorities
                );
                
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            // En caso de error en el token, el contexto se mantiene vacio
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
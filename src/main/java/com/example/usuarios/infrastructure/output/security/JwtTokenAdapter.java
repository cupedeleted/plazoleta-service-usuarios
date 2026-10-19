package com.example.usuarios.infrastructure.output.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import com.example.usuarios.domain.spi.ITokenServicePort;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class JwtTokenAdapter implements ITokenServicePort {

    private final Key secretKey;
    private final long expirationMs;

    public JwtTokenAdapter(String secret, long expirationMs) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationMs = expirationMs;
    }

    @Override
    public String createToken(String correo, String rol, Long userId) {
        return Jwts.builder()
                .setSubject(correo)
                .claim("role", rol)
                .claim("userId", rol)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(secretKey)
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            parse(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getUsernameFromToken(String token) {
        return parse(token).getSubject();
    }

    @Override
    public String getRoleFromToken(String token) {
        return parse(token).get("role", String.class);
    }

    private Claims parse(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build()
                .parseClaimsJws(token).getBody();
    }
}
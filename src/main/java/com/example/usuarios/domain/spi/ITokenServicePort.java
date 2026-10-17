package com.example.usuarios.domain.spi;

public interface ITokenServicePort {
    String createToken(String correo, String rol);
    boolean validateToken(String token);
    String getUsernameFromToken(String token);
    String getRoleFromToken(String token);
}
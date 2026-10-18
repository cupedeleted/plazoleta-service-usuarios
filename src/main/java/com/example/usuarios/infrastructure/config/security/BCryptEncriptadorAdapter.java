package com.example.usuarios.infrastructure.config.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.usuarios.domain.spi.EncriptadorClavePort;

public class BCryptEncriptadorAdapter implements EncriptadorClavePort {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String encriptar(String clave) {
        return encoder.encode(clave);
    }
}
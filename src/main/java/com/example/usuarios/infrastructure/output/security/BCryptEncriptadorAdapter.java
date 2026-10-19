package com.example.usuarios.infrastructure.output.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.usuarios.domain.spi.EncriptadorClavePort;

public class BCryptEncriptadorAdapter implements EncriptadorClavePort {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String encriptar(String clave) {
        return encoder.encode(clave);
    }

    @Override 
    public boolean coincide(String claveSinEncriptar, String claveEncriptada){
        return encoder.matches(claveSinEncriptar, claveEncriptada);
    }
}
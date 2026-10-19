package com.example.usuarios.domain.exception;

public class CredencialesInvalidas extends RuntimeException {
    public CredencialesInvalidas() {
        super("Correo o clave incorrectos");
    }
}
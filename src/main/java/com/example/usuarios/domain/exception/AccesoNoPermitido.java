package com.example.usuarios.domain.exception;

public class AccesoNoPermitido extends RuntimeException {
    public AccesoNoPermitido(String mensaje) {
        super(mensaje);
    }
}
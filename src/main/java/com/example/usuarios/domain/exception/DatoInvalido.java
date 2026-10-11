package com.example.usuarios.domain.exception;

public class DatoInvalido extends RuntimeException {
    public DatoInvalido(String mensaje){
        super(mensaje);
}
}

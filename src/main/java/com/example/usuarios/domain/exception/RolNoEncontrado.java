package com.example.usuarios.domain.exception;

public class RolNoEncontrado extends RuntimeException {
    public RolNoEncontrado(String nombre){
        super("No existe el rol: " + nombre );
    }
}
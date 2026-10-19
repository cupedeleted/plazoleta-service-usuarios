package com.example.usuarios.domain.exception;

public class ServicioNoDisponible extends RuntimeException{
    public ServicioNoDisponible(String mensaje){
        super(mensaje);
    }
}

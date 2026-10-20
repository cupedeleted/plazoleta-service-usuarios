package com.example.usuarios.domain.exception;

public class UsuarioNoEncontrado extends RuntimeException {
    public UsuarioNoEncontrado(Long id){
        super("No existe un usuario con id: " + id );
    }
}
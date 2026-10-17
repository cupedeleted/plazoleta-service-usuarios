package com.example.usuarios.domain.spi;

import com.example.usuarios.domain.model.User;

public interface UsuarioRepositoryPort {
    User guardarUsuario (User usuario);
    
}

package com.example.usuarios.domain.spi;

import com.example.usuarios.domain.model.Usuario;

public interface UsuarioRepositoryPort {
    Usuario guardarUsuario (Usuario usuario);
    
}

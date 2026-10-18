package com.example.usuarios.domain.api;

import com.example.usuarios.domain.model.Usuario;

public interface UsuarioServicePort {
    Usuario crearPropietario(Usuario usuario);
    Usuario crearCliente(Usuario usuario);
}

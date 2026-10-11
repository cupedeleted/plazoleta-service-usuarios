package com.example.usuarios.domain.spi;

import java.util.Optional;

import com.example.usuarios.domain.model.Rol;

public interface RolRepositoryPort {
    Optional<Rol> buscarPorNombre(String nombre);
}

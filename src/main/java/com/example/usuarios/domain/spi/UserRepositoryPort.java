package com.example.usuarios.domain.spi;

import com.example.usuarios.domain.model.User;

import java.util.Optional;

public interface UserRepositoryPort {
    User guardarUsuario(User usuario);
    Optional<User> buscarPorCorreo(String correo);
    boolean existePorCorreo(String correo);
    Optional<User> buscarPorId(Long id);
}

package com.example.usuarios.domain.spi;

import com.example.usuarios.domain.model.User;

public interface IUserPersistencePort {
    void saveUser(User userModel);
    boolean existsByCorreo(String correo);
}
package com.example.usuarios.domain.api;

import com.example.usuarios.domain.model.User;

public interface IUsuarioServicePort {
    User crearPropietario(User usuario);
    User crearEmpleado(User empleado, Long idRestaurante, String correoPropietario);
    String obtenerRol(Long id);
}

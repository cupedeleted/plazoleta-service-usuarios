package com.example.usuarios.domain.api;

import com.example.usuarios.application.dto.AuthResponseDto;

public interface IAuthServicePort {
    AuthResponseDto login(String correo, String clave);
}
package com.example.usuarios.application.usecase;

import com.example.usuarios.application.dto.AuthResponseDto;
import com.example.usuarios.domain.api.IAuthServicePort;
import com.example.usuarios.domain.api.ITokenServicePort;

public class AuthUseCase implements IAuthServicePort {

    private final ITokenServicePort tokenServicePort; // O el adaptador de seguridad/usuario

    public AuthUseCase(ITokenServicePort tokenServicePort) {
        this.tokenServicePort = tokenServicePort;
    }

    @Override
    public AuthResponseDto login(String correo, String clave) {
        // Lógica de validación de credenciales y generación de token
        // ...
        return new AuthResponseDto("jwt-token-generado");
    }
}
package com.example.usuarios.application.handler;

import com.example.usuarios.application.dto.AuthRequestDto;
import com.example.usuarios.application.dto.AuthResponseDto;
import com.example.usuarios.domain.api.IAuthServicePort;

public class AuthHandler {

    private final IAuthServicePort authServicePort;

    public AuthHandler(IAuthServicePort authServicePort) {
        this.authServicePort = authServicePort;
    }

    public AuthResponseDto login(AuthRequestDto dto) {
        String token = authServicePort.login(dto.getCorreo(), dto.getClave());
        return new AuthResponseDto(token);
    }
}
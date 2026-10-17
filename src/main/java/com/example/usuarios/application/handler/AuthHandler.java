package com.example.usuarios.application.handler;

import com.example.usuarios.application.dto.AuthRequestDto;
import com.example.usuarios.application.dto.AuthResponseDto;
import com.example.usuarios.domain.api.IAuthServicePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthHandler {

    private final IAuthServicePort authServicePort;

    public AuthHandler(IAuthServicePort authServicePort) {
        this.authServicePort = authServicePort;
    }

    public AuthResponseDto login(AuthRequestDto authRequestDto) {
        return authServicePort.login(authRequestDto.getCorreo(), authRequestDto.getClave());
    }
}
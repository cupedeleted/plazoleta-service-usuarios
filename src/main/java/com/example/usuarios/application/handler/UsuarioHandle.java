package com.example.usuarios.application.handler;

import com.example.usuarios.domain.api.IUsuarioServicePort;
import com.example.usuarios.domain.model.User;
import com.example.usuarios.application.dto.UserResponseDto;
import com.example.usuarios.application.mapper.UsuarioRestMapper;

public class UsuarioHandle {
    private final IUsuarioServicePort usuarioServicePort;
    private final UsuarioRestMapper usuarioRestMapper;

    public UsuarioHandle(IUsuarioServicePort usuarioServicePort, UsuarioRestMapper usuarioRestMapper) {
        this.usuarioServicePort = usuarioServicePort;
        this.usuarioRestMapper = usuarioRestMapper;
    }

    public UserResponseDto crearPropietario(UserResponseDto solicitud) {
        User usuario = usuarioRestMapper.toDomain(solicitud);
        User creado = usuarioServicePort.crearPropietario(usuario);
        return usuarioRestMapper.toResponse(creado);
    }
}


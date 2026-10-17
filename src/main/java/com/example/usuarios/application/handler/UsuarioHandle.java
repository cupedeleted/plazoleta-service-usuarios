package com.example.usuarios.application.handler;

import com.example.usuarios.domain.api.UsuarioServicePort;
import com.example.usuarios.domain.model.User;
import com.example.usuarios.application.dto.UsuarioDto;
import com.example.usuarios.application.dto.UsuarioResponseDto;
import com.example.usuarios.application.mapper.UsuarioRestMapper;

public class UsuarioHandle {
    private final UsuarioServicePort usuarioServicePort;
    private final UsuarioRestMapper usuarioRestMapper;

    public UsuarioHandle(UsuarioServicePort usuarioServicePort, UsuarioRestMapper usuarioRestMapper) {
        this.usuarioServicePort = usuarioServicePort;
        this.usuarioRestMapper = usuarioRestMapper;
    }

    public UsuarioResponseDto crearPropietario(UsuarioDto solicitud) {
        User usuario = usuarioRestMapper.toDomain(solicitud);
        User creado = usuarioServicePort.crearPropietario(usuario);
        return usuarioRestMapper.toResponse(creado);
    }
}


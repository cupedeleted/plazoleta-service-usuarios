package com.example.usuarios.application.handle;

import com.example.usuarios.domain.api.UsuarioServicePort;
import com.example.usuarios.domain.model.Usuario;
import com.example.usuarios.infrastructure.input.rest.dto.UsuarioDto;
import com.example.usuarios.infrastructure.input.rest.dto.UsuarioResponseDto;
import com.example.usuarios.infrastructure.input.rest.mapper.UsuarioRestMapper;

public class UsuarioHandle {
    private final UsuarioServicePort usuarioServicePort;
    private final UsuarioRestMapper usuarioRestMapper;

    public UsuarioHandle(UsuarioServicePort usuarioServicePort, UsuarioRestMapper usuarioRestMapper) {
        this.usuarioServicePort = usuarioServicePort;
        this.usuarioRestMapper = usuarioRestMapper;
    }

    public UsuarioResponseDto crearPropietario(UsuarioDto solicitud) {
        Usuario usuario = usuarioRestMapper.toDomain(solicitud);
        Usuario creado = usuarioServicePort.crearPropietario(usuario);
        return usuarioRestMapper.toResponse(creado);
    }

    public UsuarioResponseDto crearCliente(UsuarioDto solicitud) {
        Usuario usuario = usuarioRestMapper.toDomain(solicitud);
        Usuario creado = usuarioServicePort.crearCliente(usuario);
        return usuarioRestMapper.toResponse(creado);
    }
}

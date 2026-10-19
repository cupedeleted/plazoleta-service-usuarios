package com.example.usuarios.application.handler;

import com.example.usuarios.domain.api.IUsuarioServicePort;
import com.example.usuarios.domain.model.User;
import com.example.usuarios.application.dto.EmployeeRequestDto;
import com.example.usuarios.application.dto.UserRequestDto;
import com.example.usuarios.application.dto.UserResponseDto;
import com.example.usuarios.application.mapper.UsuarioRestMapper;

public class UsuarioHandle {
    private final IUsuarioServicePort usuarioServicePort;
    private final UsuarioRestMapper usuarioRestMapper;

    public UsuarioHandle(IUsuarioServicePort usuarioServicePort, UsuarioRestMapper usuarioRestMapper) {
        this.usuarioServicePort = usuarioServicePort;
        this.usuarioRestMapper = usuarioRestMapper;
    }

    public UserResponseDto crearPropietario(UserRequestDto userRequestDto) {
        User user = usuarioRestMapper.toDomain(userRequestDto);
        User creado = usuarioServicePort.crearPropietario(user);
        return usuarioRestMapper.toResponse(creado);
    }

    public UserResponseDto crearEmpleado(EmployeeRequestDto solicitud, String correoPropietario) {
        User empleado = usuarioRestMapper.toDomain(solicitud);
        User creado = usuarioServicePort.crearEmpleado(empleado, solicitud.getIdRestaurante(), correoPropietario);
        return usuarioRestMapper.toResponse(creado);
    }
}


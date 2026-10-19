package com.example.usuarios.application.mapper;

import com.example.usuarios.domain.model.User;
import com.example.usuarios.application.dto.UserRequestDto;
import com.example.usuarios.application.dto.UserResponseDto;

public class UsuarioRestMapper {
    public User toDomain(UserRequestDto dto) {
        User usuario = new User();
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setDocumentoDeIdentidad(dto.getDocumentoDeIdentidad());
        usuario.setCelular(dto.getCelular());
        usuario.setFechaNacimiento(dto.getFechaNacimiento());
        usuario.setCorreo(dto.getCorreo());
        usuario.setClave(dto.getClave());
        return usuario;
    }

    public UserResponseDto toResponse(User usuario) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setApellido(usuario.getApellido());
        dto.setDocumentoDeIdentidad(usuario.getDocumentoDeIdentidad());
        dto.setCelular(usuario.getCelular());
        dto.setFechaNacimiento(usuario.getFechaNacimiento());
        dto.setCorreo(usuario.getCorreo());
        dto.setRol(usuario.getRol() != null ? usuario.getRol().getNombre() : null);
        return dto;
    }
}

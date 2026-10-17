package com.example.usuarios.application.mapper;

import com.example.usuarios.domain.model.User;
import com.example.usuarios.application.dto.UsuarioDto;
import com.example.usuarios.application.dto.UsuarioResponseDto;

public class UsuarioRestMapper {
    public User toDomain(UsuarioDto dto) {
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

    public UsuarioResponseDto toResponse(User usuario) {
        UsuarioResponseDto dto = new UsuarioResponseDto();
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

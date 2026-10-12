package com.example.usuarios.infrastructure.input.rest.mapper;

import com.example.usuarios.domain.model.Usuario;
import com.example.usuarios.infrastructure.input.rest.dto.UsuarioDto;
import com.example.usuarios.infrastructure.input.rest.dto.UsuarioResponseDto;

public class UsuarioRestMapper {
    public Usuario toDomain(UsuarioDto dto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setDocumentoDeIdentidad(dto.getDocumentoDeIdentidad());
        usuario.setCelular(dto.getCelular());
        usuario.setFechaNacimiento(dto.getFechaNacimiento());
        usuario.setCorreo(dto.getCorreo());
        usuario.setClave(dto.getClave());
        return usuario;
    }

    public UsuarioResponseDto toResponse(Usuario usuario) {
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

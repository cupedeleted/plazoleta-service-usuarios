package com.example.usuarios.infrastructure.output.jpa.adapter;

import com.example.usuarios.domain.model.User;
import com.example.usuarios.domain.spi.UsuarioRepositoryPort;
import com.example.usuarios.infrastructure.output.jpa.entity.UsuarioEntity;
import com.example.usuarios.infrastructure.output.jpa.mapper.UsuarioMapper;
import com.example.usuarios.infrastructure.output.jpa.repository.UsuarioRepository;

public class UsuarioAdapter implements UsuarioRepositoryPort {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioAdapter(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public User guardarUsuario(User usuario) {
        UsuarioEntity guardar = usuarioRepository.save(usuarioMapper.toEntity(usuario));
        return usuarioMapper.toDomain(guardar);
    }

}

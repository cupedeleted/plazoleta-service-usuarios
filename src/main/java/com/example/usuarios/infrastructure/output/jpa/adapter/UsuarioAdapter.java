package com.example.usuarios.infrastructure.output.jpa.adapter;

import com.example.usuarios.infrastructure.output.jpa.mapper.UsuarioEntityMapper;
import com.example.usuarios.domain.model.User;
import com.example.usuarios.domain.spi.UserRepositoryPort;
import com.example.usuarios.infrastructure.output.jpa.entity.UserEntity;
import com.example.usuarios.infrastructure.output.jpa.repository.UserRepository;

import java.util.Optional;

public class UsuarioAdapter implements UserRepositoryPort {
    private final UserRepository usuarioRepository;
    private final UsuarioEntityMapper usuarioMapper;

    public UsuarioAdapter(UserRepository usuarioRepository, UsuarioEntityMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public User guardarUsuario(User usuario) {
        UserEntity guardar = usuarioRepository.save(usuarioMapper.toEntity(usuario));
        return usuarioMapper.toDomain(guardar);
    }

    // UsuarioAdapter (agregar)
    @Override
    public Optional<User> buscarPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo).map(usuarioMapper::toDomain);
    }

    @Override
    public boolean existePorCorreo(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }

    @Override
    public Optional<User> buscarPorId(Long id){
        return usuarioRepository.findById(id).map(usuarioMapper::toDomain);
    }

}

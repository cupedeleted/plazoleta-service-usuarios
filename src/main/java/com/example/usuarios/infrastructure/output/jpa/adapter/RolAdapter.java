package com.example.usuarios.infrastructure.output.jpa.adapter;

import java.util.Optional;

import com.example.usuarios.infrastructure.output.jpa.mapper.RolEntityMapper;
import com.example.usuarios.domain.model.Rol;
import com.example.usuarios.domain.spi.RolRepositoryPort;
import com.example.usuarios.infrastructure.output.jpa.repository.RolRepository;


public class RolAdapter implements RolRepositoryPort{
    private final RolRepository rolRepository;
    private  final RolEntityMapper rolMapper;

    
    public RolAdapter(RolRepository rolRepository, RolEntityMapper rolMapper) {
        this.rolRepository = rolRepository;
        this.rolMapper = rolMapper;
    }


    @Override
    public Optional<Rol> buscarPorNombre(String nombre) {
        return  rolRepository.findByNombre(nombre).map(rolMapper::toDomain);
    }
    
}

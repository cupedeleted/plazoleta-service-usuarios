package com.example.usuarios.infrastructure.output.jpa.mapper;

import com.example.usuarios.domain.model.Rol;
import com.example.usuarios.infrastructure.output.jpa.entity.RolEntity;

public class RolMapper {
    public Rol toDomain(RolEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Rol(entity.getId(), entity.getNombre(), entity.getDescripcion());
    }

    public RolEntity toEntity(Rol rol) {
        if (rol == null) {
            return null;
        }
        RolEntity entity = new RolEntity();
        entity.setId(rol.getId());
        entity.setNombre(rol.getNombre());
        entity.setDescripcion(rol.getDescripcion());
        return entity;
    }
}
package com.example.usuarios.infrastructure.output.jpa.mapper;

import com.example.usuarios.domain.model.User;
import com.example.usuarios.infrastructure.output.jpa.entity.UsuarioEntity;

public class UsuarioMapper {

        private final RolMapper rolMapper;

        public UsuarioMapper(RolMapper rolMapper) {
            this.rolMapper = rolMapper;
        }
        
    public User toDomain(UsuarioEntity usuarioEntity){
        if(usuarioEntity == null){
            return  null;

}
        User usuario = new User();
        usuario.setId(usuarioEntity.getId());
        usuario.setNombre(usuarioEntity.getNombre());
        usuario.setApellido(usuarioEntity.getApellido());
        usuario.setDocumentoDeIdentidad(usuarioEntity.getDocumentoDeIdentidad());
        usuario.setCelular(usuarioEntity.getCelular());
        usuario.setFechaNacimiento(usuarioEntity.getFechaNacimiento());
        usuario.setCorreo(usuarioEntity.getCorreo());
        usuario.setClave(usuarioEntity.getClave());
        usuario.setRol(rolMapper.toDomain(usuarioEntity.getRol()));
        return usuario;
}
        public UsuarioEntity toEntity(User usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioEntity entity = new UsuarioEntity();
        
        entity.setId(usuario.getId());
        entity.setNombre(usuario.getNombre());
        entity.setApellido(usuario.getApellido());
        entity.setDocumentoDeIdentidad(usuario.getDocumentoDeIdentidad());
        entity.setCelular(usuario.getCelular());
        entity.setFechaNacimiento(usuario.getFechaNacimiento());
        entity.setCorreo(usuario.getCorreo());
        entity.setClave(usuario.getClave());
        entity.setRol(rolMapper.toEntity(usuario.getRol()));
        return entity;
    }
}


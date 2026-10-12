package com.example.usuarios.infrastructure.output.jpa.mapper;

import com.example.usuarios.domain.model.Usuario;
import com.example.usuarios.infrastructure.output.jpa.entity.UsuarioEntity;

public class UsuarioMapper {

        private final RolMapper rolMapper;

        public UsuarioMapper(RolMapper rolMapper) {
            this.rolMapper = rolMapper;
        }
        
    public Usuario toDomain(UsuarioEntity usuarioEntity){
        if(usuarioEntity == null){
            return  null;

}
        Usuario usuario = new Usuario();
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
        public UsuarioEntity toEntity(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioEntity entity = new UsuarioEntity();
        // En el dominio el id es long: 0 significa "usuario nuevo", y JPA necesita null
        entity.setId(usuario.getId() == 0 ? null : usuario.getId());
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


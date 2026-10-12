package com.example.usuarios.infrastructure.output.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.usuarios.infrastructure.output.jpa.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository <UsuarioEntity, Long> {
    
}

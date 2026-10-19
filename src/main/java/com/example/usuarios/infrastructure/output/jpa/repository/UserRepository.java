package com.example.usuarios.infrastructure.output.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.usuarios.infrastructure.output.jpa.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository <UserEntity, Long> {
    Optional<UserEntity> findByCorreo(String correo);
    boolean existsByCorreo(String correo);
}

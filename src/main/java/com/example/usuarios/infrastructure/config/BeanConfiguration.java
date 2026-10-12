package com.example.usuarios.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.usuarios.application.handle.UsuarioHandle;
import com.example.usuarios.infrastructure.input.rest.mapper.UsuarioRestMapper;
import com.example.usuarios.application.usecase.UsuarioUseCase;
import com.example.usuarios.domain.api.UsuarioServicePort;
import com.example.usuarios.domain.spi.EncriptadorClavePort;
import com.example.usuarios.domain.spi.RolRepositoryPort;
import com.example.usuarios.domain.spi.UsuarioRepositoryPort;
import com.example.usuarios.infrastructure.output.jpa.adapter.RolAdapter;
import com.example.usuarios.infrastructure.output.jpa.adapter.UsuarioAdapter;
import com.example.usuarios.infrastructure.output.jpa.mapper.RolMapper;
import com.example.usuarios.infrastructure.output.jpa.mapper.UsuarioMapper;
import com.example.usuarios.infrastructure.output.jpa.repository.RolRepository;
import com.example.usuarios.infrastructure.output.jpa.repository.UsuarioRepository;
import com.example.usuarios.infrastructure.security.BCryptEncriptadorAdapter;

@Configuration
public class BeanConfiguration {
    @Bean
    public RolMapper rolMapper() {
        return new RolMapper();
    }

    @Bean
    public UsuarioMapper usuarioMapper(RolMapper rolMapper) {
        return new UsuarioMapper(rolMapper);
    }

    @Bean
    public UsuarioRepositoryPort usuarioRepositoryPort(UsuarioRepository usuarioRepository,
            UsuarioMapper usuarioMapper) {
        return new UsuarioAdapter(usuarioRepository, usuarioMapper);

    }

    @Bean
    public RolRepositoryPort rolRepositoryPort(RolRepository rolRepository, RolMapper rolMapper) {
        return new RolAdapter(rolRepository, rolMapper);
    }

    @Bean
    public EncriptadorClavePort encriptadorClavePor() {
        return new BCryptEncriptadorAdapter();
    }

    @Bean
    public UsuarioServicePort usuarioServicePort(UsuarioRepositoryPort usuarioRepositoryPort,
            RolRepositoryPort rolRepositoryPort, EncriptadorClavePort encriptadorClavePort) {
        return new UsuarioUseCase(usuarioRepositoryPort, rolRepositoryPort, encriptadorClavePort);
    }

    @Bean
    public UsuarioRestMapper usuarioRestMapper() {
        return new UsuarioRestMapper();
    }

    @Bean
    public UsuarioHandle usuarioHandle(UsuarioServicePort usuarioServicePort,
            UsuarioRestMapper usuarioRestMapper) {
        return new UsuarioHandle(usuarioServicePort, usuarioRestMapper);
    }
}

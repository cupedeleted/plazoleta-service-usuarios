package com.example.usuarios.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.usuarios.application.handler.UsuarioHandle;
import com.example.usuarios.application.mapper.UsuarioRestMapper;
import com.example.usuarios.application.usecase.UsuarioUseCase;
import com.example.usuarios.domain.api.IUsuarioServicePort;
import com.example.usuarios.domain.spi.EncriptadorClavePort;
import com.example.usuarios.domain.spi.IRestaurantPersistencePort;
import com.example.usuarios.domain.spi.RolRepositoryPort;
import com.example.usuarios.domain.spi.UsuarioRepositoryPort;
import com.example.usuarios.infrastructure.config.security.BCryptEncriptadorAdapter;
import com.example.usuarios.infrastructure.output.jpa.adapter.RestaurantHttpAdapter;
import com.example.usuarios.infrastructure.output.jpa.adapter.RolAdapter;
import com.example.usuarios.infrastructure.output.jpa.adapter.UsuarioAdapter;
import com.example.usuarios.infrastructure.output.jpa.mapper.RolMapper;
import com.example.usuarios.infrastructure.output.jpa.mapper.UsuarioMapper;
import org.springframework.web.client.RestClient;

import com.example.usuarios.infrastructure.output.jpa.repository.RolRepository;
import com.example.usuarios.infrastructure.output.jpa.repository.UsuarioRepository;

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
    public IUsuarioServicePort usuarioServicePort(UsuarioRepositoryPort usuarioRepositoryPort,
            RolRepositoryPort rolRepositoryPort, EncriptadorClavePort encriptadorClavePort) {
        return new UsuarioUseCase(usuarioRepositoryPort, rolRepositoryPort, encriptadorClavePort);
    }

    @Bean
    public UsuarioRestMapper usuarioRestMapper() {
        return new UsuarioRestMapper();
    }

    @Bean
    public UsuarioHandle usuarioHandle(IUsuarioServicePort usuarioServicePort,
            UsuarioRestMapper usuarioRestMapper) {
        return new UsuarioHandle(usuarioServicePort, usuarioRestMapper);
    }

    @Bean
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort(RestClient.Builder restClientBuilder,
            @Value("${plaza.service.url:http://localhost:8082}") String plazaServiceUrl) {
        return new RestaurantHttpAdapter(restClientBuilder, plazaServiceUrl);
    }
}

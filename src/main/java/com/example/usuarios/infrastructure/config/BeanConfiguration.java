package com.example.usuarios.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import com.example.usuarios.application.handler.AuthHandler;
import com.example.usuarios.application.handler.UsuarioHandle;
import com.example.usuarios.application.mapper.UsuarioRestMapper;
import com.example.usuarios.application.usecase.AuthUseCase;
import com.example.usuarios.application.usecase.UserUseCase;
import com.example.usuarios.domain.api.IAuthServicePort;
import com.example.usuarios.domain.api.IUsuarioServicePort;
import com.example.usuarios.domain.spi.EncriptadorClavePort;
import com.example.usuarios.domain.spi.IRestaurantPersistencePort;
import com.example.usuarios.domain.spi.ITokenServicePort;
import com.example.usuarios.domain.spi.RolRepositoryPort;
import com.example.usuarios.domain.spi.UserRepositoryPort;
import com.example.usuarios.infrastructure.output.http.RestaurantHttpAdapter;
import com.example.usuarios.infrastructure.output.jpa.adapter.RolAdapter;
import com.example.usuarios.infrastructure.output.jpa.adapter.UsuarioAdapter;
import com.example.usuarios.infrastructure.output.jpa.mapper.RolEntityMapper;
import com.example.usuarios.infrastructure.output.jpa.mapper.UsuarioEntityMapper;
import com.example.usuarios.infrastructure.output.jpa.repository.RolRepository;
import com.example.usuarios.infrastructure.output.jpa.repository.UserRepository;
import com.example.usuarios.infrastructure.output.security.BCryptEncriptadorAdapter;
import com.example.usuarios.infrastructure.output.security.JwtTokenAdapter;

@Configuration
public class BeanConfiguration {

    // ---------- Persistencia ----------
    @Bean
    public RolEntityMapper rolMapper() {
        return new RolEntityMapper();
    }

    @Bean
    public UsuarioEntityMapper usuarioMapper(RolEntityMapper rolMapper) {
        return new UsuarioEntityMapper(rolMapper);
    }

    @Bean
    public UserRepositoryPort usuarioRepositoryPort(UserRepository usuarioRepository,
            UsuarioEntityMapper usuarioMapper) {
        return new UsuarioAdapter(usuarioRepository, usuarioMapper);
    }

    @Bean
    public RolRepositoryPort rolRepositoryPort(RolRepository rolRepository, RolEntityMapper rolMapper) {
        return new RolAdapter(rolRepository, rolMapper);
    }

    // ---------- Seguridad ----------
    @Bean
    public EncriptadorClavePort encriptadorClavePort() {
        return new BCryptEncriptadorAdapter();
    }

    @Bean
    public ITokenServicePort tokenServicePort(@Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration-ms:86400000}") long expirationMs) {
        return new JwtTokenAdapter(secret, expirationMs);
    }

    // ---------- Servicio externo ----------
    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort(
            @Value("${plaza.service.url:http://localhost:8082}") String plazaServiceUrl) {
        return new RestaurantHttpAdapter(RestClient.builder(), plazaServiceUrl);
    }

    // ---------- Casos de uso y handlers ----------
    @Bean
    public IUsuarioServicePort usuarioServicePort(UserRepositoryPort usuarioRepositoryPort,
            RolRepositoryPort rolRepositoryPort, EncriptadorClavePort encriptadorClavePort) {
        return new UserUseCase(usuarioRepositoryPort, rolRepositoryPort, encriptadorClavePort);
    }

    @Bean
    public IAuthServicePort authServicePort(UserRepositoryPort usuarioRepositoryPort,
            EncriptadorClavePort encriptadorClavePort, ITokenServicePort tokenServicePort) {
        return new AuthUseCase(usuarioRepositoryPort, encriptadorClavePort, tokenServicePort);
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
    public AuthHandler authHandler(IAuthServicePort authServicePort) {
        return new AuthHandler(authServicePort);
    }
}
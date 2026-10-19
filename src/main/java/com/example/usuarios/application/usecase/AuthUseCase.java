package com.example.usuarios.application.usecase;

import com.example.usuarios.domain.api.IAuthServicePort;
import com.example.usuarios.domain.exception.CredencialesInvalidas;
import com.example.usuarios.domain.model.User;
import com.example.usuarios.domain.spi.EncriptadorClavePort;
import com.example.usuarios.domain.spi.ITokenServicePort;
import com.example.usuarios.domain.spi.UserRepositoryPort;

public class AuthUseCase implements IAuthServicePort {

    private final UserRepositoryPort usuarioRepositoryPort;
    private final EncriptadorClavePort encriptadorClavePort;
    private final ITokenServicePort tokenServicePort;

    public AuthUseCase(UserRepositoryPort usuarioRepositoryPort,
                       EncriptadorClavePort encriptadorClavePort,
                       ITokenServicePort tokenServicePort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
        this.encriptadorClavePort = encriptadorClavePort;
        this.tokenServicePort = tokenServicePort;
    }

    @Override
    public String login(String correo, String clave) {
        User usuario = usuarioRepositoryPort.buscarPorCorreo(correo)
                .filter(u -> encriptadorClavePort.coincide(clave, u.getClave()))
                .orElseThrow(CredencialesInvalidas::new);

        return tokenServicePort.createToken(usuario.getCorreo(), usuario.getRol().getNombre(), usuario.getId());
    }
}
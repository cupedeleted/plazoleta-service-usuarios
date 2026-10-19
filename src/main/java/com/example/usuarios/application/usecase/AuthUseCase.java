package com.example.usuarios.application.usecase;

import com.example.usuarios.application.dto.AuthResponseDto;
import com.example.usuarios.domain.api.IAuthServicePort;
import com.example.usuarios.domain.api.ITokenServicePort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class AuthUseCase implements IAuthServicePort {

    private final AuthenticationManager authenticationManager;
    private final ITokenServicePort tokenServicePort;

    public AuthUseCase(AuthenticationManager authenticationManager, ITokenServicePort tokenServicePort) {
        this.authenticationManager = authenticationManager;
        this.tokenServicePort = tokenServicePort;
    }

    @Override
    public AuthResponseDto login(String correo, String clave) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(correo, clave)
        );

        String userEmail = authentication.getName();
        String rol = authentication.getAuthorities().iterator().next().getAuthority();

        // Si la autenticación es exitosa, generar el token JWT a través del puerto SPI
        String token = tokenServicePort.createToken(userEmail, rol);

        return new AuthResponseDto(token);
    }
}
package com.example.usuarios.application.usecase;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.usuarios.domain.exception.DatoInvalido;
import com.example.usuarios.domain.model.Usuario;
import com.example.usuarios.domain.spi.EncriptadorClavePort;
import com.example.usuarios.domain.spi.RolRepositoryPort;
import com.example.usuarios.domain.spi.UsuarioRepositoryPort;

@ExtendWith(MockitoExtension.class)
class UsuarioUseCaseTest {

    @Mock
    private UsuarioRepositoryPort usuarioRepositoryPort;

    @Mock
    private RolRepositoryPort rolRepositoryPort;

    @Mock
    private EncriptadorClavePort encriptadorClavePort;

    @InjectMocks
    private UsuarioUseCase usuarioUseCase;

    @Test
    void seCreaCorrectamente() {
        assertNotNull(usuarioUseCase);
    }

    @Test
    void sinDatos() {
        Usuario usuario = new Usuario();

        assertThrows(DatoInvalido.class, () -> usuarioUseCase.crearPropietario(usuario));
    }
}
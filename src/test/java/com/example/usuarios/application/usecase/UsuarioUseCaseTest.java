package com.example.usuarios.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.usuarios.domain.exception.DatoInvalido;
import com.example.usuarios.domain.exception.RolNoEncontrado;
import com.example.usuarios.domain.model.Rol;
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
    
    private Usuario registroValido(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Carla");
        usuario.setApellido("Granados");
        usuario.setDocumentoDeIdentidad("1130268043");
        usuario.setCelular("3043147466");
        usuario.setFechaNacimiento(LocalDate.of(2006, 12, 4));
        usuario.setCorreo("Frinklefrinkle4@gmail.com");
        usuario.setClave("123");
        return usuario;
    }
        @Test 
        void seCreaCorrectamente(){
            assertNotNull(usuarioUseCase);
        }
        @Test 
        void noHayDatos(){
            Usuario usuario= new Usuario();
            assertThrows(DatoInvalido.class, () -> usuarioUseCase.crearPropietario(usuario));
        }
        @Test 
        void documentoConLetras(){
            Usuario usuario= registroValido();
            usuario.setDocumentoDeIdentidad("12as");
            assertThrows(DatoInvalido.class,() -> usuarioUseCase.crearPropietario(usuario));
    }
        @Test 
        void celularMuyLargo(){
            Usuario usuario = registroValido();
            usuario.setCelular("+5730056983251");
            assertThrows(DatoInvalido.class, () -> usuarioUseCase.crearPropietario(usuario));
    
        }
        @Test 
        void correoInvalido(){
            Usuario usuario = registroValido();
            usuario.setCorreo("azk12");
            assertThrows(DatoInvalido.class, () -> usuarioUseCase.crearPropietario(usuario));   
        }
        @Test 
        void mayorEdad(){
            Usuario usuario = registroValido();
            usuario.setFechaNacimiento(LocalDate.now().minusYears(10));
            assertThrows(DatoInvalido.class, () -> usuarioUseCase.crearPropietario(usuario));
        }  
        @Test 
        void rolNoEncontrado() {
            Usuario usuario = registroValido();
            when(rolRepositoryPort.buscarPorNombre("PROPIETARIO")).thenReturn(Optional.empty());
            assertThrows(RolNoEncontrado.class,() -> usuarioUseCase.crearPropietario(usuario));
    }
        @Test 
        void datosValidos(){
            Usuario usuario = registroValido();
            Rol rol = new Rol(1L, "PROPIETARIO", "Propietario");
            when(rolRepositoryPort.buscarPorNombre("PROPIETARIO")).thenReturn(Optional.of(rol));
            when(encriptadorClavePort.encriptar("123")).thenReturn("clave");
            when(usuarioRepositoryPort.guardarUsuario(usuario)).thenReturn(usuario);

            Usuario resultado = usuarioUseCase.crearPropietario(usuario);
            assertEquals("PROPIETARIO", resultado.getRol().getNombre());
            assertEquals("clave", resultado.getClave());
            verify(usuarioRepositoryPort).guardarUsuario(usuario);
    }
}
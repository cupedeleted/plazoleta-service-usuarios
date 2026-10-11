package com.example.usuarios.application.usecase;
import com.example.usuarios.domain.exception.DatoInvalido;
import com.example.usuarios.domain.api.UsuarioServicePort;
import com.example.usuarios.domain.model.Usuario;
import com.example.usuarios.domain.spi.EncriptadorClavePort;
import com.example.usuarios.domain.spi.RolRepositoryPort;
import com.example.usuarios.domain.spi.UsuarioRepositoryPort;
import java.util.regex.Pattern;

    public class UsuarioUseCase implements UsuarioServicePort{
        private static final Pattern patron = Pattern.compile("^\\d+$");
        private static final Pattern max_telefono = Pattern.compile("^\\+?\\d+$");
        private final UsuarioRepositoryPort usuarioRepositoryPort;
        private final RolRepositoryPort rolRepositoryPort;
        private final EncriptadorClavePort encriptadorClavePort;

    public UsuarioUseCase(UsuarioRepositoryPort usuarioRepositoryPort, RolRepositoryPort rolRepositoryPort,
                EncriptadorClavePort encriptadorClavePort) {
            this.usuarioRepositoryPort = usuarioRepositoryPort;
            this.rolRepositoryPort = rolRepositoryPort;
            this.encriptadorClavePort = encriptadorClavePort;
        }

    @Override
    public Usuario crearPropietario(Usuario usuario) {
        ValidarCampos(usuario);
        validarDocumento(usuario.getDocumentoDeIdentidad());
        validarCelular(usuario.getCelular());
        return usuarioRepositoryPort.guardarUsuario(usuario);
    }

    private void ValidarCampos(Usuario u) {
        if(vacio(u.getNombre()) || vacio(u.getApellido()) || vacio(u.getDocumentoDeIdentidad()) || vacio(u.getCelular())
            || u.getFechaNacimiento() == null || vacio(u.getCorreo()) || vacio(u.getClave())) {
        throw new DatoInvalido("Todos los campos son obligatorios");
        }
    }

    private boolean vacio(String text){
    return text == null || text.isBlank();
    }

    private void validarDocumento(String documento){
        if(!patron.matcher(documento).matches()){
            throw new DatoInvalido("El documento de identidad debe ser unicamente numerico");
        }
    }
    private void validarCelular(String telefono){
        if(telefono.length() > 13 || max_telefono.matcher(telefono).matches()){
            throw new DatoInvalido("El celular debe tener maximo 13 caracteres y puede iniciar con +");
        }
    }
}
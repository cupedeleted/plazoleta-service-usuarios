package com.example.usuarios.application.usecase;
import com.example.usuarios.domain.exception.DatoInvalido;
import com.example.usuarios.domain.api.UsuarioServicePort;
import com.example.usuarios.domain.model.Usuario;
import com.example.usuarios.domain.spi.EncriptadorClavePort;
import com.example.usuarios.domain.spi.RolRepositoryPort;
import com.example.usuarios.domain.spi.UsuarioRepositoryPort;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.Period;

    public class UsuarioUseCase implements UsuarioServicePort{
        private static final Pattern patron = Pattern.compile("^\\d+$");
        private static final Pattern max_telefono = Pattern.compile("^\\+?\\d+$");
        private static final Pattern email_validar = Pattern.compile("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$");
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
        validarCampos(usuario);
        validarDocumento(usuario.getDocumentoDeIdentidad());
        validarCelular(usuario.getCelular());
        validarCorreo(usuario.getCorreo());
        validarMayorEdad(usuario.getFechaNacimiento());
        return usuarioRepositoryPort.guardarUsuario(usuario);
    }

    private void validarCampos(Usuario u) {
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
        if(telefono.length() > 13 || !max_telefono.matcher(telefono).matches()){
            throw new DatoInvalido("El celular debe tener maximo 13 caracteres y puede iniciar con +");
        }
    }
    private void validarCorreo(String email){
        if(!email_validar.matcher(email).matches()){
            throw new DatoInvalido("El correo no tiene un formato válido. Ejemplo: usuario@gmail.com");
        }
    }
    private void validarMayorEdad(LocalDate fechaDeNacimiento){
        int edad = Period.between(fechaDeNacimiento, LocalDate.now()).getYears();
            if(edad < 18){
            throw new DatoInvalido("El usuario debe ser mayor de edad");    
         }
    }
}
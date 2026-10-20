package com.example.usuarios.application.usecase;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

import com.example.usuarios.domain.api.IUsuarioServicePort;
import com.example.usuarios.domain.exception.AccesoNoPermitido;
import com.example.usuarios.domain.exception.DatoInvalido;
import com.example.usuarios.domain.exception.RolNoEncontrado;
import com.example.usuarios.domain.exception.UsuarioNoEncontrado;
import com.example.usuarios.domain.model.Rol;
import com.example.usuarios.domain.model.User;
import com.example.usuarios.domain.spi.EncriptadorClavePort;
import com.example.usuarios.domain.spi.IRestaurantPersistencePort;
import com.example.usuarios.domain.spi.RolRepositoryPort;
import com.example.usuarios.domain.spi.UserRepositoryPort;

public class UserUseCase implements IUsuarioServicePort {
    private static final Pattern SOLO_NUMEROS = Pattern.compile("^\\d+$");
    private static final Pattern CELULAR = Pattern.compile("^\\+?\\d+$");
    private static final Pattern CORREO = Pattern.compile("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$");
    private static final String ROL_PROPIETARIO = "PROPIETARIO";
    private static final String ROL_EMPLEADO = "EMPLEADO";

    private final UserRepositoryPort usuarioRepositoryPort;
    private final RolRepositoryPort rolRepositoryPort;
    private final EncriptadorClavePort encriptadorClavePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;

    public UserUseCase(UserRepositoryPort usuarioRepositoryPort, RolRepositoryPort rolRepositoryPort,
            EncriptadorClavePort encriptadorClavePort, IRestaurantPersistencePort restaurantPersistencePort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
        this.rolRepositoryPort = rolRepositoryPort;
        this.encriptadorClavePort = encriptadorClavePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public User crearPropietario(User usuario) {
        return crearConRol(usuario, ROL_PROPIETARIO);
    }

    @Override
    public User crearEmpleado(User empleado, Long idRestaurante, String correoPropietario) {
        if (idRestaurante == null) {
            throw new DatoInvalido("El id del restaurante es obligatorio");
        }
        validarPropietarioDelRestaurante(idRestaurante, correoPropietario);
        return crearConRol(empleado, ROL_EMPLEADO);
    }

    @Override
    public String obtenerRol(Long id){
        return usuarioRepositoryPort.buscarPorId(id)
                .map(u -> u.getRol().getNombre())
                .orElseThrow(() -> new UsuarioNoEncontrado(id));
    }

    private void validarPropietarioDelRestaurante(Long idRestaurante, String correoPropietario) {
        User propietario = usuarioRepositoryPort.buscarPorCorreo(correoPropietario)
                .orElseThrow(() -> new AccesoNoPermitido("No se pudo identificar al propietario"));
        if (!restaurantPersistencePort.existsByIdAndPropietarioId(idRestaurante, propietario.getId())) {
            throw new AccesoNoPermitido("El restaurante no pertenece al propietario");
        }
    }

    private boolean vacio(String text) {
        return text == null || text.isBlank();
    }

    private void validarCampos(User u) {
        if (vacio(u.getNombre()) || vacio(u.getApellido()) || vacio(u.getDocumentoDeIdentidad())
                || vacio(u.getCelular())
                || u.getFechaNacimiento() == null || vacio(u.getCorreo()) || vacio(u.getClave())) {
            throw new DatoInvalido("Todos los campos son obligatorios");
        }
    }

    private void validarDocumento(String documento) {
        if (!SOLO_NUMEROS.matcher(documento).matches()) {
            throw new DatoInvalido("El documento de identidad debe ser unicamente numerico");
        }
    }

    private void validarCelular(String telefono) {
        if (telefono.length() > 13 || !CELULAR.matcher(telefono).matches()) {
            throw new DatoInvalido("El celular debe tener maximo 13 caracteres y puede iniciar con +");
        }
    }

    private void validarCorreo(String email) {
        if (!CORREO.matcher(email).matches()) {
            throw new DatoInvalido("El correo no tiene un formato válido. Ejemplo: usuario@gmail.com");
        }
    }

    private void validarMayorEdad(LocalDate fechaDeNacimiento) {
        int edad = Period.between(fechaDeNacimiento, LocalDate.now()).getYears();
        if (edad < 18) {
            throw new DatoInvalido("El usuario debe ser mayor de edad");
        }
    }

    private User crearConRol(User usuario, String nombreRol) {
        validarCampos(usuario);
        validarDocumento(usuario.getDocumentoDeIdentidad());
        validarCelular(usuario.getCelular());
        validarCorreo(usuario.getCorreo());
        validarMayorEdad(usuario.getFechaNacimiento());
        if (usuarioRepositoryPort.existePorCorreo(usuario.getCorreo())) {
            throw new DatoInvalido("Ya existe un usuario con ese correo");
        }
        Rol rol = rolRepositoryPort.buscarPorNombre(nombreRol)
                .orElseThrow(() -> new RolNoEncontrado(nombreRol));
        usuario.setRol(rol);
        usuario.setClave(encriptadorClavePort.encriptar(usuario.getClave()));
        return usuarioRepositoryPort.guardarUsuario(usuario);
    }

    // validarCampos, vacio, validarDocumento, validarCelular, validarCorreo y
    // validarMayorEdad quedan igual, usando las constantes renombradas
    // (SOLO_NUMEROS, CELULAR, CORREO).
}
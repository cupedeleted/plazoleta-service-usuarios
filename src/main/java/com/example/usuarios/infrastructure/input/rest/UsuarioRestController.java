package com.example.usuarios.infrastructure.input.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usuarios.application.handler.UsuarioHandle;
import com.example.usuarios.application.dto.EmployeeRequestDto;
import com.example.usuarios.application.dto.UserRequestDto;
import com.example.usuarios.application.dto.UserResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description = "Gestión de usuarios")
public class UsuarioRestController {

    private final UsuarioHandle usuarioHandle;

    public UsuarioRestController(UsuarioHandle usuarioHandle) {
        this.usuarioHandle = usuarioHandle;
    }

    @Operation(summary = "Crear propietario", description = "Solo administrador. La clave se guarda encriptada.")
    @ApiResponse(responseCode = "201", description = "Propietario creado")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "401", description = "Sin autenticación")
    @ApiResponse(responseCode = "403", description = "Requiere rol administrador")
    @PostMapping("/propietario")
    public ResponseEntity<UserResponseDto> crearPropietario(@Valid @RequestBody UserRequestDto solicitud) {
        UserResponseDto creado = usuarioHandle.crearPropietario(solicitud);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @Operation(summary = "Crear empleado",
            description = "Solo el propietario del restaurante indicado. El usuario queda con rol empleado.")
    @ApiResponse(responseCode = "201", description = "Empleado creado")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "401", description = "Sin autenticación")
    @ApiResponse(responseCode = "403", description = "No es propietario o el restaurante no es suyo")
    @PostMapping("/empleado")
    public ResponseEntity<UserResponseDto> crearEmpleado(@Valid @RequestBody EmployeeRequestDto solicitud,
            Authentication authentication) {
        UserResponseDto creado = usuarioHandle.crearEmpleado(solicitud, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }
}
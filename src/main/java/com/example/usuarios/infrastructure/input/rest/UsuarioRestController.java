package com.example.usuarios.infrastructure.input.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usuarios.application.handler.UsuarioHandle;
import com.example.usuarios.application.dto.UserResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description = "Gestión de usuarios")
public class UsuarioRestController {

    private final UsuarioHandle usuarioHandle;

    public UsuarioRestController(UsuarioHandle usuarioHandle) {
        this.usuarioHandle = usuarioHandle;
    }

    @Operation(summary = "Crear propietario", description = "Crea un usuario con rol propietario. La clave se guarda encriptada.")
    @ApiResponse(responseCode = "201", description = "Propietario creado")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "500", description = "Rol propietario no encontrado")
    @PostMapping("/propietario")
    public ResponseEntity<UserResponseDto> crearPropietario(@RequestBody UserResponseDto solicitud) {
        UserResponseDto creado = usuarioHandle.crearPropietario(solicitud);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }
}
package com.example.usuarios.infrastructure.input.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usuarios.application.handle.UsuarioHandle;
import com.example.usuarios.infrastructure.input.rest.dto.UsuarioDto;
import com.example.usuarios.infrastructure.input.rest.dto.UsuarioResponseDto;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioHandle usuarioHandle;

    public UsuarioController(UsuarioHandle usuarioHandle) {
        this.usuarioHandle = usuarioHandle;
    }

    @PostMapping("/propietario")
    public ResponseEntity<UsuarioResponseDto> crearPropietario(@RequestBody UsuarioDto request) {
        UsuarioResponseDto creado = usuarioHandle.crearPropietario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }
}
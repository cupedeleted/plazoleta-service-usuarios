package com.example.usuarios.infrastructure.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.usuarios.domain.exception.DatoInvalido;
import com.example.usuarios.domain.exception.RolNoEncontrado;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DatoInvalido.class)
    public ResponseEntity<Map<String, String>> datoInvalido(DatoInvalido ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("mensaje", ex.getMessage()));
    }

    @ExceptionHandler(RolNoEncontrado.class)
    public ResponseEntity<Map<String, String>> rolNoEncontrado(RolNoEncontrado ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("mensaje", ex.getMessage()));
    }
}
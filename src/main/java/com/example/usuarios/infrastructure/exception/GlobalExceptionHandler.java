package com.example.usuarios.infrastructure.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.example.usuarios.domain.exception.DatoInvalido;
import com.example.usuarios.domain.exception.RolNoEncontrado;
import com.example.usuarios.domain.exception.CredencialesInvalidas;
import com.example.usuarios.domain.exception.AccesoNoPermitido;

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

    @ExceptionHandler(CredencialesInvalidas.class)
    public ResponseEntity<Map<String, String>> credencialesInvalidas(CredencialesInvalidas ex) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(Map.of("mensaje", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validacion(MethodArgumentNotValidException ex) {
    String mensaje = ex.getBindingResult().getFieldErrors().stream()
            .map(e -> e.getDefaultMessage())
            .findFirst()
            .orElse("Datos inválidos");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("mensaje", mensaje));
    }

    @ExceptionHandler(AccesoNoPermitido.class)
    public ResponseEntity<Map<String, String>> accesoNoPermitido(AccesoNoPermitido ex) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("mensaje", ex.getMessage()));
    }
}
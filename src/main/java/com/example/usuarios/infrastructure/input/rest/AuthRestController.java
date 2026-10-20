package com.example.usuarios.infrastructure.input.rest;

import com.example.usuarios.application.dto.AuthRequestDto;
import com.example.usuarios.application.dto.AuthResponseDto;
import com.example.usuarios.application.handler.AuthHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthRestController {

    private final AuthHandler authHandler;

    public AuthRestController(AuthHandler authHandler) {
        this.authHandler = authHandler;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody AuthRequestDto authRequestDto) {
        AuthResponseDto responseDto = authHandler.login(authRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
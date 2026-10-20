package com.example.usuarios.application.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthRequestDto {
    @NotBlank(message = "El correo es obligatorio")
    private String correo;
    private String clave;

    @NotBlank(message = "La clave es obligatorio")
    public AuthRequestDto() {}

    public AuthRequestDto(String correo, String clave) {
        this.correo = correo;
        this.clave = clave;
    }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }
}
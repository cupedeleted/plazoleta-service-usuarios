package com.example.usuarios.application.dto;

public class AuthRequestDto {
    private String correo;
    private String clave;

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
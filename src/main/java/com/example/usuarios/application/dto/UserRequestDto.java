package com.example.usuarios.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserRequestDto {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;
    @NotBlank(message = "El documento de identidad es obligatorio")
    private String documentoDeIdentidad;
    @NotBlank(message = "El celular es obligatorio")
    private String celular;
    @NotBlank(message = "El correo es obligatorio")
    private String correo;
    @NotBlank(message = "La clave es obligatoria")
    private String clave;
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fechaNacimiento;
}
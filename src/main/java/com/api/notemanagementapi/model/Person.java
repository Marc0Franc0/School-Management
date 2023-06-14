package com.api.notemanagementapi.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
public abstract class Person {

    @NotBlank(message = "Verificar nombre ingresado")
    private String name;

    @NotBlank(message = "Verificar apellido ingresado")
    private String lastName;

    @NotBlank(message = "Verificar email ingresado")
    @Email(message = "Email no válido ")
    private String email;

    @NotBlank(message = "Verificar celular ingresado")
    private String cell_phone;
}

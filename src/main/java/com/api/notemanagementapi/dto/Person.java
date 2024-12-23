package com.api.notemanagementapi.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Embeddable
@NoArgsConstructor
public class Person {
    @NotNull(message = "Verificar DNI ingresado")
    private Integer dni;
    @NotBlank(message = "Verificar nombre ingresado")
    private String name;

    @NotBlank(message = "Verificar apellido ingresado")
    private String lastName;

    @NotBlank(message = "Verificar email ingresado")
    @Email(message = "Email no válido ")
    private String email;

    @NotBlank(message = "Verificar celular ingresado")
    private String cell_phone;

    public Person(String name, String lastName,String email,String cell_phone) {
        this.email = email;
        this.lastName = lastName;
        this.name = name;
        this.cell_phone = cell_phone;
    }
}

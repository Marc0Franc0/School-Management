package com.api.notemanagementapi.dto;

import java.util.Set;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentDto {

    @NotBlank(message = "Verificar nombre ingresado")
    private String name;

    @NotBlank(message = "Verificar apellido ingresado")
    private String lastName;

    @NotBlank(message = "Verificar email ingresado")
    @Email(message = "Email no válido ")
    private String email;

    @NotBlank(message = "Verificar celular ingresado")
    private String cell_phone;

    private Set<Long> id_subjects;
}

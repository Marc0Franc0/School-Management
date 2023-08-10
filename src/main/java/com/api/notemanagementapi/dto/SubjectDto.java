package com.api.notemanagementapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubjectDto {
    
   @NotBlank(message = "Verificar nombre ingresado")
    private String name;

    @NotBlank(message = "Verificar descripción ingresada")
    private String description;

    private Long idTeacher;
    
}

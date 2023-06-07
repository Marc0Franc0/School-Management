package com.api.notemanagementapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NoteDto {

    @NotBlank(message = "Verificar nota ingresada")
    private String note;
    @NotBlank(message = "Verificar nota ingresada")
    private String studentLastName;
    @NotBlank(message = "Verificar nota ingresada")
    private String subjectName;

    public NoteDto(String note, String studentLastName, String subjectName) {
        this.note=note;
        this.studentLastName=studentLastName;
        this.subjectName=subjectName;

    }
}

package com.api.notemanagementapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NoteDto {

    @NotBlank(message = "Verificar nota ingresada")
    private String note;
    @NotNull(message = "Verificar nota ingresada")
    private Long studentId;
    @NotNull(message = "Verificar nota ingresada")
    private Long subjectId;

    public NoteDto(String note, Long studentId, Long subjectId) {
        this.note=note;
        this.studentId=studentId;
        this.subjectId=subjectId;

    }
}

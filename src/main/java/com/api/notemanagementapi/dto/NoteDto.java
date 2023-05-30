package com.api.notemanagementapi.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NoteDto {
    
    @NotEmpty(message = "Note may not be empty")
    @NotNull (message = "Note may not be null")
    private String note;

    private Long idStudent;

    private Long idSubject;
}

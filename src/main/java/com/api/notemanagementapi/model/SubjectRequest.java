package com.api.notemanagementapi.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SubjectRequest {
    
    @NotEmpty(message = "Name may not be empty")
    @NotNull (message = "Name may not be null")
    private String name;

    @NotEmpty(message = "Description may not be empty")
    @NotNull (message = "Description may not be null")
    private String description;

    private Long idTeacher;
    
}

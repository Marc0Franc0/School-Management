package com.api.notemanagementapi.dto;

import java.util.Set;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentDto {
 
    @NotEmpty(message = "Name may not be empty")
    @NotNull (message = "Name may not be null")
    private String name;

    @NotEmpty(message = "Lastname may not be empty")
    @NotNull (message = "Lastname may not be null")
    private String lastName;

    @NotEmpty(message = "Email may not be empty")
    @NotNull (message = "Email may not be null")
    @Email(message = "Invalid email ")
    private String email;

    @NotEmpty(message = "Cell phone may not be empty")
    @NotNull (message = "Cell phone may not be null")
    private String cell_phone;

    private Set<Long> id_subjects;
}

package com.api.notemanagementapi.dto;

import java.util.Set;

import com.api.notemanagementapi.model.Person;
import lombok.Data;

@Data
public class StudentDto extends Person {
    private Set<Long> id_subjects;
}

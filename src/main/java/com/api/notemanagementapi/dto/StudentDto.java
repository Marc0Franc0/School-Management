package com.api.notemanagementapi.dto;

import java.util.Set;

import jakarta.persistence.Embedded;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StudentDto{
    @Embedded
    private Person personalData;
    private Set<Long> id_subjects;


}

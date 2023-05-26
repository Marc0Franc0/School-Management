package com.api.notemanagementapi.dto;

import lombok.Data;

@Data
public class NoteDto {

    private int note;

    private Long idStudent;

    private Long idSubject;
}

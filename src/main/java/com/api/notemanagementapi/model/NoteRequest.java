package com.api.notemanagementapi.model;

import lombok.Data;

@Data
public class NoteRequest {

    private int note;

    private Long idStudent;

    private Long idSubject;
}

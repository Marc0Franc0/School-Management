package com.api.notemanagementapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.notemanagementapi.dto.NoteDto;
import com.api.notemanagementapi.model.Note;

@Service
public interface NoteService {
    public List<Note> getAll();
    public Optional<Note> getNoteById(Long id);
    public Note createNote(NoteDto note);
    public Optional<Object> updateNoteById(Long id, NoteDto note);
    public void removeNoteById (Long id);
}

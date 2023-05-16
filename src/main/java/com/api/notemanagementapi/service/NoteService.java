package com.api.notemanagementapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.notemanagementapi.model.Note;
import com.api.notemanagementapi.model.NoteRequest;

@Service
public interface NoteService {
    public List<Note> getAll();
    public Optional<Note> getNoteById(Long id);
    public Note createNote(NoteRequest student);
    public Optional<Object> updateNoteById(Long id, NoteRequest student);
    public void removeNoteById (Long id);
}

package com.api.notemanagementapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.notemanagementapi.model.Note;
import com.api.notemanagementapi.model.NoteRequest;
import com.api.notemanagementapi.repository.NoteRepository;
import com.api.notemanagementapi.repository.StudentRepository;
import com.api.notemanagementapi.repository.SubjectRepository;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public List<Note> getAll() {
        // TODO Auto-generated method stub
        return noteRepository.findAll();
    }

    @Override
    public Optional<Note> getNoteById(Long id) {
        // TODO Auto-generated method stub
        return noteRepository.findById(id);
    }

    @Override
    public Note createNote(NoteRequest student) {
        // TODO Auto-generated method stub
        return noteRepository.save(Note
                .builder()
                .note(student.getNote())
                .student(studentRepository.findById(student.getIdStudent()).get())
                .subject(subjectRepository.findById(student.getIdSubject()).get())
                .build());
    }

    @Override
    public Optional<Object> updateNoteById(Long id, NoteRequest student) {
        // TODO Auto-generated method stub
        return noteRepository.findById(id)
                .map(note -> {
                    note = Note
                            .builder()
                            .id(id)
                            .note(student.getNote())
                            .student(studentRepository.findById(student.getIdStudent()).get())
                            .subject(subjectRepository.findById(student.getIdSubject()).get())
                            .build();
                    return noteRepository.save(note);
                });
    }

    @Override
    public void removeNoteById(Long id) {
        // TODO Auto-generated method stub
        noteRepository.deleteById(id);
    }

}

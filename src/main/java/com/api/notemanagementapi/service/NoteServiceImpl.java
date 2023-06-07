package com.api.notemanagementapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.notemanagementapi.dto.NoteDto;
import com.api.notemanagementapi.model.Note;
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
    public Note createNote(NoteDto note) {
        // TODO Auto-generated method stub
        return noteRepository.save(Note
                .builder()
                .note(note.getNote())
                .student(studentRepository.findByLastName(note.getStudentLastName()).get())
                .subject(subjectRepository.findByName(note.getSubjectName()).get())
                .build());
    }

    @Override
    public Optional<Object> updateNoteById(Long id, NoteDto note) {
        // TODO Auto-generated method stub
        return noteRepository.findById(id)
                .map(not -> {
                    not = Note
                            .builder()
                            .id(id)
                            .note(not.getNote())
                            .student(studentRepository.findByLastName(note.getStudentLastName()).get())
                            .subject(subjectRepository.findByName(note.getSubjectName()).get())
                            .build();
                    return noteRepository.save(not);
                });
    }

    @Override
    public void removeNoteById(Long id) {
        // TODO Auto-generated method stub
        noteRepository.deleteById(id);
    }

}

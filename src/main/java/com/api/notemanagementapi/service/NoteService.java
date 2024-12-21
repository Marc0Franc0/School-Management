package com.api.notemanagementapi.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.api.notemanagementapi.service.crud.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.notemanagementapi.dto.NoteDto;
import com.api.notemanagementapi.model.Note;
import com.api.notemanagementapi.repository.NoteRepository;
import com.api.notemanagementapi.repository.StudentRepository;
import com.api.notemanagementapi.repository.SubjectRepository;

@Service
public class NoteService implements CrudService {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public List<Object> getAll() {
        return Collections.singletonList(noteRepository.findAll());
    }

    @Override
    public Optional<Object> getById(Long id) {
        return Optional.of(noteRepository.findById(id));
    }

    @Override
    public Object create(Object object) {
        NoteDto note = (NoteDto)object;
        return noteRepository.save(Note
                .builder()
                .note(note.getNote())
                .student(studentRepository.findById(note.getStudentId()).get())
                .subject(subjectRepository.findById(note.getSubjectId()).get())
                .build());
    }

    @Override
    public Optional<Object> updateById(Long id, Object object) {
        NoteDto note = (NoteDto)object;
        return noteRepository.findById(id)
                .map(not -> {
                    not = Note
                            .builder()
                            .id(id)
                            .note(not.getNote())
                            .student(studentRepository.findById(note.getStudentId()).get())
                            .subject(subjectRepository.findById(note.getSubjectId()).get())
                            .build();
                    return noteRepository.save(not);
                });
    }

    @Override
    public void removeById(Long id) {
        noteRepository.deleteById(id);
    }

}

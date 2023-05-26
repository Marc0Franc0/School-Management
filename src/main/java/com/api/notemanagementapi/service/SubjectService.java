package com.api.notemanagementapi.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.api.notemanagementapi.dto.SubjectDto;
import com.api.notemanagementapi.model.Subject;

@Service
public interface SubjectService {
    public List<Subject> getAll();
    public Optional<Subject> getSubjectById(Long id);
    public Subject createSubject(SubjectDto subject);
    public Optional<Object> updateSubjectById(Long id, SubjectDto subject);
    public void removeSubjectById (Long id);
}

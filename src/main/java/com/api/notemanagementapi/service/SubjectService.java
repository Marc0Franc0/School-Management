package com.api.notemanagementapi.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.api.notemanagementapi.model.Subject;
import com.api.notemanagementapi.model.SubjectRequest;

@Service
public interface SubjectService {
    public List<Subject> getAll();
    public Optional<Subject> getSubjectById(Long id);
    public Subject createSubject(SubjectRequest subject);
    public Optional<Object> updateSubjectById(Long id, SubjectRequest subject);
    public void removeSubjectById (Long id);
}

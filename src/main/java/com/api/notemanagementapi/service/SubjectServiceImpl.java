package com.api.notemanagementapi.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.notemanagementapi.dto.SubjectDto;
import com.api.notemanagementapi.model.Subject;
import com.api.notemanagementapi.repository.SubjectRepository;
import com.api.notemanagementapi.repository.TeacherRepository;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public List<Subject> getAll() {
        // TODO Auto-generated method stub
        return subjectRepository.findAll();
    }

    @Override
    public Optional<Subject> getSubjectById(Long id) {
        // TODO Auto-generated method stub
        return subjectRepository.findById(id);
    }

    @Override
    public Subject createSubject(SubjectDto subject) {
        // TODO Auto-generated method stub
        return subjectRepository.save(Subject
                .builder()
                .name(subject.getName())
                .description(subject.getDescription())
                .teacher(teacherRepository.findById(subject.getIdTeacher()).get())
                .build());
    }

    @Override
    public Optional<Object> updateSubjectById(Long id, SubjectDto subject) {
        
        return subjectRepository.findById(id)
                .map(subj -> {
                    subj = Subject
                            .builder()
                            .id(id)
                            .name(subject.getName())
                            .description(subject.getDescription())
                            .teacher(teacherRepository.findById(subject.getIdTeacher()).get())
                            .build();
                    return subjectRepository.save(subj);
                });
    }

    @Override
    public void removeSubjectById(Long id) {
        subjectRepository.deleteById(id);
    }

}

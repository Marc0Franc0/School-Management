package com.api.notemanagementapi.service;

import java.util.List;
import java.util.Optional;

import com.api.notemanagementapi.service.crud.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.notemanagementapi.dto.SubjectDto;
import com.api.notemanagementapi.model.Subject;
import com.api.notemanagementapi.repository.SubjectRepository;
import com.api.notemanagementapi.repository.TeacherRepository;

@Service
public class SubjectService implements CrudService {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    @Override
    public Optional<Subject> getById(Long id) {
        return subjectRepository.findById(id);
    }

    @Override
    public Subject create(Object object) {
        SubjectDto subject = (SubjectDto) object;
        return subjectRepository.save(Subject
                .builder()
                .name(subject.getName())
                .description(subject.getDescription())
                .teacher(teacherRepository.findById(subject.getIdTeacher()).get())
                .build());
    }

    @Override
    public Optional<Object> updateById(Long id, Object object) {
        SubjectDto subject = (SubjectDto) object;
        
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
    public void removeById(Long id) {
        subjectRepository.deleteById(id);
    }

}

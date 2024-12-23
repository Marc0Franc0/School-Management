package com.api.notemanagementapi.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.api.notemanagementapi.dto.NoteDto;
import com.api.notemanagementapi.model.Student;
import com.api.notemanagementapi.service.crud.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.api.notemanagementapi.dto.SubjectDto;
import com.api.notemanagementapi.model.Subject;
import com.api.notemanagementapi.repository.SubjectRepository;
import com.api.notemanagementapi.repository.TeacherRepository;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final TeacherService teacherService;
    private final StudentService studentService;
    @Autowired
    public SubjectService( @Lazy StudentService studentService,
                           @Lazy TeacherService teacherService,
                           SubjectRepository subjectRepository){
        this.subjectRepository=subjectRepository;
        this.teacherService=teacherService;
        this.studentService=studentService;
    }
    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }


    public Optional<Subject> getById(Long id) {
        return subjectRepository.findById(id);
    }

    public Subject create(SubjectDto subject) {
        return subjectRepository.save(Subject
                .builder()
                .name(subject.getName())
                .description(subject.getDescription())
                .teacher(teacherService.getById(subject.getIdTeacher()).get())
                //.teacher(teacherRepository.findById(subject.getIdTeacher()).get())
                .build());
    }

    public Optional<Subject> updateById(Long id, SubjectDto subject) {
        
        return subjectRepository.findById(id)
                .map(subj -> {
                    subj = Subject
                            .builder()
                            .id(id)
                            .name(subject.getName())
                            .description(subject.getDescription())
                            .teacher(teacherService.getById(subject.getIdTeacher()).get())
                            //.teacher(teacherRepository.findById(subject.getIdTeacher()).get())
                            .build();
                    subjectRepository.save(subj);
                    return subj;
                });
    }

    public void removeById(Long id) {
        subjectRepository.deleteById(id);
    }
    public List<Subject> getById(Set<Long> ids) {
        return subjectRepository.findAllById(ids);
    }
    public Optional<List<SubjectDto>> getSubjectsByStudentId(Long id) {
        return studentService.getById(id)
                .map(stu-> stu.getSubjects()
                        .stream()
                        .map(subject->
                                new SubjectDto(
                                        subject.getName(),
                                        subject.getDescription(),
                                        subject.getTeacher().getId()))
                        .collect(Collectors.toList()));
    }

}

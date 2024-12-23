package com.api.notemanagementapi.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.api.notemanagementapi.repository.StudentRepository;
import com.api.notemanagementapi.repository.SubjectRepository;
import com.api.notemanagementapi.service.crud.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.api.notemanagementapi.dto.StudentDto;
import com.api.notemanagementapi.model.Student;

@Service
public class StudentService  {
    private final StudentRepository studentRepository;
    private final SubjectService subjectService;

    @Autowired
    public StudentService(@Lazy SubjectService subjectService,
                          StudentRepository studentRepository){
        this.studentRepository = studentRepository;
        this.subjectService=subjectService;
    }
    public List<Student> getAll(){
        return studentRepository.findAll();

    }
    public Optional<Student> getById(Long id){
        return studentRepository.findById(id);
    }
    public Student create(StudentDto student){
        return studentRepository.save( Student
                .builder()
                .name(student.getPersonalData().getName())
                .lastName(student.getPersonalData().getLastName())
                .cell_phone(student.getPersonalData().getCell_phone())
                .email(student.getPersonalData().getEmail())
                .subjects(subjectService.getById(student.getId_subjects()))
                //.subjects(subjectRepository.findAllById(student.getId_subjects()))
                .build());
    }
    public Optional<Student> updateById(Long id, StudentDto student){
        return studentRepository.findById(id)
                .map(stu -> {
                    stu  =  Student
                            .builder()
                            .id(id)
                            .name(student.getPersonalData().getName())
                            .lastName(student.getPersonalData().getLastName())
                            .email(student.getPersonalData().getEmail())
                            .cell_phone(student.getPersonalData().getCell_phone())
                            .subjects(subjectService.getById(student.getId_subjects()))
                            //.subjects(subjectRepository.findAllById(student.getId_subjects()))
                            .build();
                    studentRepository.save(stu);
                    return stu;
                });
    }

    public void removeById(Long id) {
        studentRepository.deleteById(id);
    }

}

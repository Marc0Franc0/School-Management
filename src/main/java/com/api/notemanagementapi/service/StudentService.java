package com.api.notemanagementapi.service;

import java.util.List;
import java.util.Optional;

import com.api.notemanagementapi.repository.StudentRepository;
import com.api.notemanagementapi.repository.SubjectRepository;
import com.api.notemanagementapi.service.crud.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.notemanagementapi.dto.StudentDto;
import com.api.notemanagementapi.model.Student;

@Service
public class StudentService extends StudentServiceUtils implements CrudService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    SubjectRepository subjectRepository;
    public List<Student> getAll(){
        return studentRepository.findAll();

    }
    public Optional<Student> getById(Long id){
        return studentRepository.findById(id);
    }
    public Student create(Object object){
        StudentDto student = (StudentDto) object;
        return studentRepository.save( Student
                .builder()
                .name(student.getPersonalData().getName())
                .lastName(student.getPersonalData().getLastName())
                .cell_phone(student.getPersonalData().getCell_phone())
                .email(student.getPersonalData().getEmail())
                .subjects(subjectRepository.findAllById(student.getId_subjects()))
                .build());
    }
    public Optional<Object> updateById(Long id, Object object){
        StudentDto student = (StudentDto) object;
        return studentRepository.findById(id)
                .map(stu -> {
                    stu  =  Student
                            .builder()
                            .id(id)
                            .name(student.getPersonalData().getName())
                            .lastName(student.getPersonalData().getLastName())
                            .email(student.getPersonalData().getEmail())
                            .cell_phone(student.getPersonalData().getCell_phone())
                            .subjects(subjectRepository.findAllById(student.getId_subjects()))
                            .build();
                    return studentRepository.save(stu);
                });
    }

    @Override
    public void removeById(Long id) {
        studentRepository.deleteById(id);
    }

}

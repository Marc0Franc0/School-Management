package com.api.notemanagementapi.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.notemanagementapi.model.Student;
import com.api.notemanagementapi.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;
    @Override
    public List<Student> getAll() {
       return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }
    @Override
    public Student createStudent(Student student) {
        return studentRepository.save( Student
        .builder()
        .name(student.getName())
        .lastName(student.getLastName())
        .cell_phone(student.getCell_phone())
        .email(student.getEmail())
        .build());
    }

    //pendiente
    @Override
    public Student updateStudentById(Long id, Student student) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateStudent'");
    }

    @Override
    public Optional<Student> removeStudentById(Long id) {
        studentRepository.deleteById(id);
        return studentRepository.findById(id);
    }
    
}

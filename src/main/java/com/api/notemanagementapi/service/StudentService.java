package com.api.notemanagementapi.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.api.notemanagementapi.dto.StudentDto;
import com.api.notemanagementapi.model.Student;

@Service
public interface StudentService {
    public List<Student> getAll();
    public Optional<Student> getStudentById(Long id);
    public Student createStudent(StudentDto student);
    public Optional<Object> updateStudentById(Long id, StudentDto student);
    public void removeStudentById (Long id);
}

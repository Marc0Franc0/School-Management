package com.api.notemanagementapi.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.api.notemanagementapi.model.Student;

@Service
public interface StudentService {
    public List<Student> getAll();
    public Optional<Student> getStudentById(Long id);
    public Student createStudent(Student student);
    public Student updateStudentById(Long id, Student student);
    public Optional<Student> removeStudentById (Long id);
}

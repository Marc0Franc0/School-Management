package com.api.notemanagementapi.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.api.notemanagementapi.model.Student;
import com.api.notemanagementapi.model.StudentRequest;

@Service
public interface StudentService {
    public List<Student> getAll();
    public Optional<Student> getStudentById(Long id);
    public Student createStudent(StudentRequest student);
    public Optional<Object> updateStudentById(Long id, StudentRequest student);
    public void removeStudentById (Long id);
}

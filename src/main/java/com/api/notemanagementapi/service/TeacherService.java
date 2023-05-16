package com.api.notemanagementapi.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.api.notemanagementapi.model.Teacher;
import com.api.notemanagementapi.model.TeacherRequest;

@Service
public interface TeacherService {
    public List<Teacher> getAll();
    public Optional<Teacher> getTeacherById(Long id);
    public Teacher createTeacher(TeacherRequest teacher);
    public Optional<Object> updateTeacherById(Long id, TeacherRequest teacher);
    public void removeTeacherById (Long id);
}

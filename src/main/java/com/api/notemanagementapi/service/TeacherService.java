package com.api.notemanagementapi.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.api.notemanagementapi.dto.TeacherDto;
import com.api.notemanagementapi.model.Teacher;

@Service
public interface TeacherService {
    public List<Teacher> getAll();
    public Optional<Teacher> getTeacherById(Long id);
    public Teacher createTeacher(TeacherDto teacher);
    public Optional<Object> updateTeacherById(Long id, TeacherDto teacher);
    public void removeTeacherById (Long id);
}

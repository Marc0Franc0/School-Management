package com.api.notemanagementapi.service;

import java.util.List;
import java.util.Optional;

import com.api.notemanagementapi.service.crud.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.notemanagementapi.dto.TeacherDto;
import com.api.notemanagementapi.model.Teacher;
import com.api.notemanagementapi.repository.TeacherRepository;

@Service
public class TeacherService implements CrudService {
    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> getById(Long id) {
        return teacherRepository.findById(id);
    }

    @Override
    public Object create(Object object) {
        TeacherDto teacher = (TeacherDto)object;
        // TODO Auto-generated method stub
        return teacherRepository.save(Teacher
                .builder()
                .name(teacher.getName())
                .lastName(teacher.getLastName())
                .cell_phone(teacher.getCell_phone())
                .email(teacher.getEmail())
                .build());
    }

    @Override
    public Optional updateById(Long id, Object object) {
        TeacherDto teacher = (TeacherDto)object;
        // TODO Auto-generated method stub
        return teacherRepository.findById(id)
                .map(tch -> {
                    tch = Teacher
                            .builder()
                            .id(id)
                            .name(teacher.getName())
                            .lastName(teacher.getLastName())
                            .email(teacher.getEmail())
                            .cell_phone(teacher.getCell_phone())
                            .build();
                    return teacherRepository.save(tch);
                });
    }

    @Override
    public void removeById(Long id) {
        teacherRepository.deleteById(id);
    }

}

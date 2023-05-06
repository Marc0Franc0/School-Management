package com.api.notemanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.notemanagementapi.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher,Long>{
    
}

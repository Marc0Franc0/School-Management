package com.api.notemanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.notemanagementapi.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long>{
    
}

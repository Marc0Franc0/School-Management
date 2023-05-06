package com.api.notemanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.notemanagementapi.model.Student;

public interface StudentRepository extends JpaRepository<Student,Long>{
    
}

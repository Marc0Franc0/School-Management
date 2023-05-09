package com.api.notemanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.notemanagementapi.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{
    
}

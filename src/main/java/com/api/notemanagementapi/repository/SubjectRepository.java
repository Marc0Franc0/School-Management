package com.api.notemanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.notemanagementapi.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
    
}

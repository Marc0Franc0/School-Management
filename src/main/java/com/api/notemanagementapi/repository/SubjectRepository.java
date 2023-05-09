package com.api.notemanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.notemanagementapi.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {
    
}

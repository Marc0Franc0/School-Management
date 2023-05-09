package com.api.notemanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.notemanagementapi.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long>{
    
}

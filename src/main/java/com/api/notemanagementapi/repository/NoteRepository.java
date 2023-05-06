package com.api.notemanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.notemanagementapi.model.Note;

public interface NoteRepository extends JpaRepository<Note,Long>{
    
}

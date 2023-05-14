package com.api.notemanagementapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity 
@Table(name = "notes")
//Representa las notas de los estudiantes por cada materia
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    float note;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "student_id")
    Student student;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "subject_id")
    Subject subject;
    
}

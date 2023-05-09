package com.api.notemanagementapi.model;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String lastName;
    String email;
    String cell_phone;
    
    //Sets students of subjects
    //many-to-many relationship with two columns -> "student_id" and "subject_id"
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "students_subjects", 
        joinColumns = { @JoinColumn(name = "student_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "subject_id") }
    )
    Set<Subject> subjects = new HashSet<>();

    //Sets student notes
    @OneToMany(mappedBy = "student")
    Set<Note> notes;
}
package com.api.notemanagementapi.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotEmpty(message = "Name may not be empty")
    @NotNull (message = "Name may not be null")
    String name;

    @NotEmpty(message = "Lastname may not be empty")
    @NotNull (message = "Lastname may not be null")
    String lastName;

    @NotEmpty(message = "Email may not be empty")
    @NotNull (message = "Email may not be null")
    @Email(message = "Invalid email ")
    String email;

    @NotEmpty(message = "Cell phone may not be empty")
    @NotNull (message = "Cell phone may not be null")
    String cell_phone;
    
    //Sets students of subjects
    //many-to-many relationship with two columns -> "student_id" and "subject_id"
    @JsonIgnore
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
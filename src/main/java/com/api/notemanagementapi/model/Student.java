package com.api.notemanagementapi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
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
  @Column(unique = true,nullable = false)
  private Integer dni;
  private String name;

  private String lastName;

  @Column(unique = true)
  private String email;

  @Override
  public String toString() {
    return "Student{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", cell_phone='" + cell_phone + '\'' +
            ", IDsubjects=" + subjects.stream().map(subject -> subject.getId()).collect(Collectors.toList()) +
            '}';
  }

  private String cell_phone;

  //Materias de los estudiantes
  //Relación muchos a muchos -> "student_id" <-> "subject_id"
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "students_subjects", joinColumns = { @JoinColumn(name = "student_id") }, inverseJoinColumns = {
      @JoinColumn(name = "subject_id") })
  List<Subject> subjects = new ArrayList<>();
  @JsonIgnore
  //Notas de los alumnos
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "student", cascade = CascadeType.ALL)
  private List<Note> notes;
}
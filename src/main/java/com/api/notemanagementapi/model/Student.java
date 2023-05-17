package com.api.notemanagementapi.model;

import java.util.ArrayList;
import java.util.List;
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

  @Column(unique = true)
  private String name;

  private String lastName;

  @Column(unique = true)
  private String email;

  private String cell_phone;

  // Sets students of subjects
  // many-to-many relationship with two columns -> "student_id" and "subject_id"
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "students_subjects", joinColumns = { @JoinColumn(name = "student_id") }, inverseJoinColumns = {
      @JoinColumn(name = "subject_id") })
  List<Subject> subjects = new ArrayList<>();

  // Sets student notes
  @JsonIgnore
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "student", cascade = CascadeType.ALL)
  private List<Note> notes;
}
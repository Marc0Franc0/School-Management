package com.api.notemanagementapi.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String name;

    private String lastName;
    
    @Column(unique = true)
    private String email;
    
    private String cell_phone;
    
    //Se establecen las materias a cargo del profesor
    @OneToMany(fetch = FetchType.EAGER,mappedBy =  "teacher", cascade = CascadeType.ALL)
    private List<Subject> subjects = new ArrayList<>(); 
}

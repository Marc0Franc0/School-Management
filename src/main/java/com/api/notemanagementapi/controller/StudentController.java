package com.api.notemanagementapi.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.notemanagementapi.model.Student;
import com.api.notemanagementapi.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {

   @Autowired
   StudentService studentService;

   @GetMapping("/students")
   public ResponseEntity<List<Student>> getAll() {
      return ResponseEntity.status(HttpStatus.OK).body(studentService.getAll());
   }

   @GetMapping("/student/{id}")
   public void getStudent(@PathVariable Long id) {
      Optional<Student> student = studentService.getStudentById(id);
        student
       .stream()
       .findFirst()
       .ifPresentOrElse(s-> {ResponseEntity.status(HttpStatus.OK).body(s);},
       ()-> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found"));

     
   }

   @PostMapping("/students")
   public ResponseEntity<String> createStudent(@RequestBody Student student) {
      Student studentCreated = studentService.createStudent(student);
      if (studentCreated != null) {
         return ResponseEntity.status(HttpStatus.OK).body("Student created");
      } else {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Student not created");
      }

   }

   @PutMapping("/student/{id}")
   public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody Student student) {
      Student studentModified = studentService.updateStudentById(id, student);
      if (studentModified != null) {
         return ResponseEntity.status(HttpStatus.OK).body("Student modified");
      } else {
         return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Student not found");
      }

   }

   @DeleteMapping("/student/{id}")
   public void removeStudentById(@PathVariable Long id) {
      Optional<Student> student = studentService.removeStudentById(id);
      student
      .stream()
      .findFirst()
      .ifPresentOrElse(s->ResponseEntity.status(HttpStatus.OK).body("Student deleted"), 
       ()-> ResponseEntity.status(HttpStatus.NO_CONTENT).body("Student not found"));
   }
}

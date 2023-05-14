package com.api.notemanagementapi.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentController {

   @Autowired
   StudentService studentService;

   @GetMapping("/")
   public ResponseEntity<List<Student>> getAll() {
      return ResponseEntity.status(HttpStatus.OK).body(studentService.getAll());
   }

   @GetMapping("/{id}")
   public ResponseEntity<Student> getStudent(@PathVariable Long id) {
      Optional<Student> student = studentService.getStudentById(id);
      return ResponseEntity.status(HttpStatus.OK).body(student.get());

   }

   @PostMapping("/")
   public ResponseEntity<String> createStudent(@Valid @RequestBody Student student, BindingResult bindingResult) {
      if (bindingResult.hasErrors()) {
         return ResponseEntity
               .status(HttpStatus.BAD_REQUEST)
               .body(bindingResult.getFieldError().getDefaultMessage());
      } else {
         studentService.createStudent(student);
         return ResponseEntity
               .status(HttpStatus.OK)
               .body("Student created");
      }

   }

   @PutMapping("/{id}")
   public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody Student student) {

      if (studentService.getStudentById(id).isPresent()) {

         studentService.updateStudentById(id, student);

         return ResponseEntity.status(HttpStatus.OK).body("Student modified");
      } else {
         return ResponseEntity.status(HttpStatus.OK).body("Student not found");
      }

   }

   @DeleteMapping("/{id}")
   public ResponseEntity<String> removeStudentById(@PathVariable Long id) {

      if (studentService.getStudentById(id).isPresent()) {
         studentService.removeStudentById(id);
         return ResponseEntity.status(HttpStatus.OK).body("Student deleted");
      } else {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Student not found");
      }

   }
}

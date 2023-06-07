package com.api.notemanagementapi.controller;

import java.util.List;
import java.util.Optional;

import com.api.notemanagementapi.dto.NoteDto;
import com.api.notemanagementapi.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.api.notemanagementapi.dto.StudentDto;
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

   @GetMapping("/{id}/notes")
   public ResponseEntity<Optional<List<NoteDto>>> getNotesById(@PathVariable Long id) {
      return ResponseEntity.status(HttpStatus.OK).body(studentService.getNotesById(id));
   }
   @GetMapping("/notes")
   public ResponseEntity<Optional<List<NoteDto>>> getNotesByLastName(@RequestParam String lastName) {
      return ResponseEntity.status(HttpStatus.OK).body(studentService.getNotesByLastname(lastName));
   }

   @PostMapping("/")
   public ResponseEntity<String> createStudent(@Valid @RequestBody StudentDto student, BindingResult bindingResult) {
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
   public ResponseEntity<String> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentDto student
   ,BindingResult bindingResult) {

      if (studentService.getStudentById(id).isPresent()) {
         if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(bindingResult.getFieldError().getDefaultMessage());
         } else {
            studentService.updateStudentById(id, student);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Student modified");
         }

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

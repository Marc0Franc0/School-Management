package com.api.notemanagementapi.controller;

import java.util.List;
import java.util.Optional;

import com.api.notemanagementapi.dto.NoteDto;
import com.api.notemanagementapi.model.Note;
import com.api.notemanagementapi.service.crud.CrudService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.api.notemanagementapi.dto.StudentDto;
import com.api.notemanagementapi.model.Student;
import com.api.notemanagementapi.service.StudentService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

   @Autowired
   private StudentService studentService;

   @GetMapping("/")
   public ResponseEntity<List<Student>> getAll() {
      return ResponseEntity.status(HttpStatus.OK).body(studentService.getAll());
   }

   @GetMapping("/{id}")
   public ResponseEntity<Student> getStudent(@PathVariable Long id) {
      Optional<Student> student = studentService.getById(id);
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

   @GetMapping("/{id}/subjects")
   public ResponseEntity<Optional<List<String>>> getSubjectsById(@PathVariable Long id) {
      return ResponseEntity.status(HttpStatus.OK).body(studentService.getSubjectsById(id));
   }
   @GetMapping("/subjects")
   public ResponseEntity<Optional<List<String>>> getSubjectsByLastName(@RequestParam String lastName) {
      return ResponseEntity.status(HttpStatus.OK).body(studentService.getSubjectsByLastName(lastName));
   }


   @PostMapping("/")
   public ResponseEntity<String> createStudent(@Valid @RequestBody StudentDto student, BindingResult bindingResult) {
      if (bindingResult.hasErrors()) {
         return ResponseEntity
               .status(HttpStatus.BAD_REQUEST)
               .body(bindingResult.getFieldError().getDefaultMessage());
      } else {
         studentService.create(student);
         return ResponseEntity
               .status(HttpStatus.OK)
               .body("Student created");
      }

   }

   @PutMapping("/{id}")
   public ResponseEntity<String> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentDto student
   ,BindingResult bindingResult) {

      if (studentService.getById(id).isPresent()) {
         if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(bindingResult.getFieldError().getDefaultMessage());
         } else {
            studentService.updateById(id, student);
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

      if (studentService.getById(id).isPresent()) {
         studentService.removeById(id);
         return ResponseEntity.status(HttpStatus.OK).body("Student deleted");
      } else {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Student not found");
      }

   }
}

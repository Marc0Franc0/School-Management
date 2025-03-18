package com.api.notemanagementapi.controller;

import java.util.List;
import java.util.Optional;

import com.api.notemanagementapi.dto.NoteDto;
import com.api.notemanagementapi.model.Note;
import com.api.notemanagementapi.service.crud.CrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
   @Operation(summary = "Get all students", responses = {
           @ApiResponse(description = "Successful Operation", responseCode = "200",
                   content = @Content(mediaType = "application/json",
                           schema = @Schema(implementation = List.class))),
           @ApiResponse(responseCode = "404", description = "Not found",
                   content = @Content)})
   @GetMapping("/")
   public ResponseEntity<List<Student>> getAll() {
      return ResponseEntity.status(HttpStatus.OK).body(studentService.getAll());
   }
   @Operation(summary = "Get student by student ID", responses = {
           @ApiResponse(description = "Successful Operation", responseCode = "200",
                   content = @Content(mediaType = "application/json",
                           schema = @Schema(implementation = Student.class))),
           @ApiResponse(responseCode = "404", description = "Not found",
                   content = @Content)})
   @GetMapping("/{id}")
   public ResponseEntity<Student> getStudent(@PathVariable Long id) {
      Optional<Student> student = studentService.getById(id);
      return ResponseEntity.status(HttpStatus.OK).body(student.get());

   }
   @Operation(summary = "Create student", responses = {
           @ApiResponse(description = "Successful Operation", responseCode = "200",
                   content = @Content(mediaType = "application/json",
                           schema = @Schema(implementation = String.class))),
           @ApiResponse(responseCode = "404", description = "Not found",
                   content = @Content)})
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
   @Operation(summary = "Update student by student ID", responses = {
           @ApiResponse(description = "Successful Operation", responseCode = "200",
                   content = @Content(mediaType = "application/json",
                           schema = @Schema(implementation = String.class))),
           @ApiResponse(responseCode = "404", description = "Not found",
                   content = @Content)})
   @PutMapping("/{id}")
   public ResponseEntity<String> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentDto student
   ,BindingResult bindingResult) {

      if (studentService.getById(id).isPresent()) {
         if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(bindingResult.getFieldError().getDefaultMessage());
         } else {
            Optional<Student> stu = studentService.updateById(id, student);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Student modified, ".concat(student.toString()));
         }

      } else {
         return ResponseEntity.status(HttpStatus.OK).body("Student not found");
      }

   }
   @Operation(summary = "Remove student by student ID", responses = {
           @ApiResponse(description = "Successful Operation", responseCode = "200",
                   content = @Content(mediaType = "application/json",
                           schema = @Schema(implementation = String.class))),
           @ApiResponse(responseCode = "404", description = "Not found",
                   content = @Content)})
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

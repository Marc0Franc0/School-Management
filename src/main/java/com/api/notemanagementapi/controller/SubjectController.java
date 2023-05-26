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

import com.api.notemanagementapi.dto.SubjectDto;
import com.api.notemanagementapi.model.Subject;
import com.api.notemanagementapi.service.SubjectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @GetMapping("/")
   public ResponseEntity<List<Subject>> getAll() {
      return ResponseEntity.status(HttpStatus.OK).body(subjectService.getAll());
   }

   @GetMapping("/{id}")
   public ResponseEntity<Subject> getSubject(@PathVariable Long id) {
      Optional<Subject> subject = subjectService.getSubjectById(id);
      return ResponseEntity.status(HttpStatus.OK).body(subject.get());
   }

   @PostMapping("/")
   public ResponseEntity<String> createSubject(@Valid @RequestBody SubjectDto subject, BindingResult bindingResult ){
      if (bindingResult.hasErrors()) {
         return ResponseEntity
               .status(HttpStatus.BAD_REQUEST)
               .body(bindingResult.getFieldError().getDefaultMessage());
      } else {
         subjectService.createSubject(subject);
         return ResponseEntity
               .status(HttpStatus.OK)
               .body("Subject created");
      }
   }

   @PutMapping("/{id}")
   public ResponseEntity<String> updateSubject(@PathVariable Long id,@RequestBody SubjectDto subject) {
      if (subjectService.getSubjectById(id).isPresent()) {

         subjectService.updateSubjectById(id, subject);

         return ResponseEntity.status(HttpStatus.OK).body("Subject modified");
      } else {
         return ResponseEntity.status(HttpStatus.OK).body("Subject not found");
      }
    
   }

    @DeleteMapping("/{id}")
   public ResponseEntity<String> removeSubjectById(@PathVariable Long id) {
      
      if (subjectService.getSubjectById(id).isPresent()) {
         subjectService.removeSubjectById(id);
         return ResponseEntity.status(HttpStatus.OK).body("Subject deleted");
      } else {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Subject not found");
      }
   }
}

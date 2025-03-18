package com.api.notemanagementapi.controller;

import java.util.List;
import java.util.Optional;

import com.api.notemanagementapi.service.SubjectService;
import com.api.notemanagementapi.service.crud.CrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.api.notemanagementapi.dto.SubjectDto;
import com.api.notemanagementapi.model.Subject;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/subjects")
public class SubjectController {
   @Autowired
    private SubjectService subjectService;
   @Operation(summary = "Get all subjects", responses = {
           @ApiResponse(description = "Successful Operation", responseCode = "200",
                   content = @Content(mediaType = "application/json",
                           schema = @Schema(implementation = List.class))),
           @ApiResponse(responseCode = "404", description = "Not found",
                   content = @Content)})
    @GetMapping("/")
   public ResponseEntity<List<Subject>> getAll() {
      return ResponseEntity.status(HttpStatus.OK).body(subjectService.getAll());
   }
   @Operation(summary = "Get subject by subject ID", responses = {
           @ApiResponse(description = "Successful Operation", responseCode = "200",
                   content = @Content(mediaType = "application/json",
                           schema = @Schema(implementation = Subject.class))),
           @ApiResponse(responseCode = "404", description = "Not found",
                   content = @Content)})
   @GetMapping("/{id}")
   public ResponseEntity<Subject> getSubject(@PathVariable Long id) {
      Optional<Subject> subject = subjectService.getById(id);
      return ResponseEntity.status(HttpStatus.OK).body(subject.get());
   }
   @Operation(summary = "Create subject", responses = {
           @ApiResponse(description = "Successful Operation", responseCode = "200",
                   content = @Content(mediaType = "application/json",
                           schema = @Schema(implementation = String.class))),
           @ApiResponse(responseCode = "404", description = "Not found",
                   content = @Content)})
   @PostMapping("/")
   public ResponseEntity<String> createSubject(@Valid @RequestBody SubjectDto subject, BindingResult bindingResult ){
      if (bindingResult.hasErrors()) {
         return ResponseEntity
               .status(HttpStatus.BAD_REQUEST)
               .body(bindingResult.getFieldError().getDefaultMessage());
      } else {
         Subject subj=subjectService.create(subject);
         return ResponseEntity
               .status(HttpStatus.OK)
               .body("Subject created, ".concat(subj.toString()));
      }
   }
   @Operation(summary = "Update subject by subject ID", responses = {
           @ApiResponse(description = "Successful Operation", responseCode = "200",
                   content = @Content(mediaType = "application/json",
                           schema = @Schema(implementation = String.class))),
           @ApiResponse(responseCode = "404", description = "Not found",
                   content = @Content)})
   @PutMapping("/{id}")
   public ResponseEntity<String> updateSubject(@PathVariable Long id,@Valid @RequestBody SubjectDto subject,
                                               BindingResult bindingResult) {
      if (subjectService.getById(id).isPresent()) {

         if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(bindingResult.getFieldError().getDefaultMessage());
         } else {
            Optional<Subject> subj=subjectService.updateById(id, subject);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Subject modified, ".concat(subj.toString()));
         }

      } else {
         return ResponseEntity.status(HttpStatus.OK).body("Subject not found");
      }
    
   }
   @Operation(summary = "Remove subject by subject ID", responses = {
           @ApiResponse(description = "Successful Operation", responseCode = "200",
                   content = @Content(mediaType = "application/json",
                           schema = @Schema(implementation = String.class))),
           @ApiResponse(responseCode = "404", description = "Not found",
                   content = @Content)})
    @DeleteMapping("/{id}")
   public ResponseEntity<String> removeSubjectById(@PathVariable Long id) {
      
      if (subjectService.getById(id).isPresent()) {
         subjectService.removeById(id);
         return ResponseEntity.status(HttpStatus.OK).body("Subject deleted");
      } else {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Subject not found");
      }
   }
   @Operation(summary = "Get subjects by student ID", responses = {
           @ApiResponse(description = "Successful Operation", responseCode = "200",
                   content = @Content(mediaType = "application/json",
                           schema = @Schema(implementation = List.class))),
           @ApiResponse(responseCode = "404", description = "Not found",
                   content = @Content)})
  @GetMapping("/students/{id}")
   public ResponseEntity<Optional<List<SubjectDto>>> getSubjectsById(@PathVariable Long id) {
      return ResponseEntity.status(HttpStatus.OK).body(subjectService.getSubjectsByStudentId(id));
   }

}

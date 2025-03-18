package com.api.notemanagementapi.controller;

import java.util.List;
import java.util.Optional;

import com.api.notemanagementapi.service.NoteService;
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

import com.api.notemanagementapi.dto.NoteDto;
import com.api.notemanagementapi.model.Note;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/notes")
public class NoteController {
   @Autowired
   private NoteService noteService;
   @Operation(summary = "Get all notes", responses = {
           @ApiResponse(description = "Successful Operation", responseCode = "200",
                   content = @Content(mediaType = "application/json",
                           schema = @Schema(implementation = String.class))),
           @ApiResponse(responseCode = "404", description = "Not found",
                   content = @Content)})
   @GetMapping("/")
   public ResponseEntity<List<Note>> getAll() {
      return ResponseEntity.status(HttpStatus.OK).body(noteService.getAll());
   }
   @Operation(summary = "Get student notes by student ID", responses = {
           @ApiResponse(description = "Successful Operation", responseCode = "200",
                   content = @Content(mediaType = "application/json",
                           schema = @Schema(implementation = Note.class))),
           @ApiResponse(responseCode = "404", description = "Not found",
                   content = @Content)})
   @GetMapping("/{id}")
   public ResponseEntity<Note> getNotes(@PathVariable Long id) {
      Optional<Note> student = noteService.getById(id);
      return ResponseEntity.status(HttpStatus.OK).body(student.get());

   }
   @Operation(summary = "Create student note", responses = {
           @ApiResponse(description = "Successful Operation", responseCode = "200",
                   content = @Content(mediaType = "application/json",
                           schema = @Schema(implementation = String.class))),
           @ApiResponse(responseCode = "404", description = "Not found",
                   content = @Content)})
   @PostMapping("/")
   public ResponseEntity<String> createNote(@Valid @RequestBody NoteDto note,
         BindingResult bindingResult) {
      if (bindingResult.hasErrors()) {
         return ResponseEntity
               .status(HttpStatus.BAD_REQUEST)
               .body(bindingResult.getFieldError().getDefaultMessage());
      } else {
         Note note1=noteService.create(note);
         return ResponseEntity
               .status(HttpStatus.OK)
               .body("Note created, ".concat(note1.toString()));
      }

   }
   @Operation(summary = "Update student note by student ID", responses = {
           @ApiResponse(description = "Successful Operation", responseCode = "200",
                   content = @Content(mediaType = "application/json",
                           schema = @Schema(implementation = String.class))),
           @ApiResponse(responseCode = "404", description = "Not found",
                   content = @Content)})
   @PutMapping("/{id}")
   public ResponseEntity<String> updateNote( @PathVariable Long id,@Valid @RequestBody NoteDto note) {

      if (noteService.getById(id).isPresent()) {

         Optional<Note> noteCreated =noteService.updateById(id, note);

         return ResponseEntity.status(HttpStatus.OK).body("Note modified, ".concat(noteCreated.toString()));
      } else {
         return ResponseEntity.status(HttpStatus.OK).body("Note not found");
      }

   }
   @Operation(summary = "Remove student note by student ID", responses = {
           @ApiResponse(description = "Successful Operation", responseCode = "200",
                   content = @Content(mediaType = "application/json",
                           schema = @Schema(implementation = String.class))),
           @ApiResponse(responseCode = "404", description = "Not found",
                   content = @Content)})
   @DeleteMapping("/{id}")
   public ResponseEntity<String> removeNoteById(@PathVariable Long id) {

      if (noteService.getById(id).isPresent()) {
         noteService.removeById(id);
         return ResponseEntity.status(HttpStatus.OK).body("Note deleted");
      } else {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Note not found");
      }

   }
   @Operation(summary = "Get notes by student ID", responses = {
           @ApiResponse(description = "Successful Operation", responseCode = "200",
                   content = @Content(mediaType = "application/json",
                           schema = @Schema(implementation = List.class))),
           @ApiResponse(responseCode = "404", description = "Not found",
                   content = @Content)})
   @GetMapping("/students/{id}")
   public ResponseEntity<Optional<List<NoteDto>>> getNotesByStudentId(@PathVariable Long id) {
      return ResponseEntity.status(HttpStatus.OK).body(noteService.getNotesByStudentId(id));
   }
}

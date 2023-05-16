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
import com.api.notemanagementapi.model.Note;
import com.api.notemanagementapi.model.NoteRequest;
import com.api.notemanagementapi.service.NoteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

   @Autowired
   NoteService noteService;

   @GetMapping("/")
   public ResponseEntity<List<Note>> getAll() {
      return ResponseEntity.status(HttpStatus.OK).body(noteService.getAll());
   }

   @GetMapping("/{id}")
   public ResponseEntity<Note> getStudent(@PathVariable Long id) {
      Optional<Note> student = noteService.getNoteById(id);
      return ResponseEntity.status(HttpStatus.OK).body(student.get());

   }

   @PostMapping("/")
   public ResponseEntity<String> createStudent(@Valid @RequestBody NoteRequest note,
         BindingResult bindingResult) {
      if (bindingResult.hasErrors()) {
         return ResponseEntity
               .status(HttpStatus.BAD_REQUEST)
               .body(bindingResult.getFieldError().getDefaultMessage());
      } else {
         noteService.createNote(note);
         return ResponseEntity
               .status(HttpStatus.OK)
               .body("Note created");
      }

   }

   @PutMapping("/{id}")
   public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody NoteRequest note) {

      if (noteService.getNoteById(id).isPresent()) {

         noteService.updateNoteById(id, note);

         return ResponseEntity.status(HttpStatus.OK).body("Note modified");
      } else {
         return ResponseEntity.status(HttpStatus.OK).body("Note not found");
      }

   }

   @DeleteMapping("/{id}")
   public ResponseEntity<String> removeStudentById(@PathVariable Long id) {

      if (noteService.getNoteById(id).isPresent()) {
         noteService.removeNoteById(id);
         return ResponseEntity.status(HttpStatus.OK).body("Note deleted");
      } else {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Note not found");
      }

   }
}

package com.api.notemanagementapi.controller;

import java.util.List;
import java.util.Optional;

import com.api.notemanagementapi.service.NoteService;
import com.api.notemanagementapi.service.crud.CrudService;
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

   @GetMapping("/")
   public ResponseEntity<List<Note>> getAll() {
      return ResponseEntity.status(HttpStatus.OK).body(noteService.getAll());
   }

   @GetMapping("/{id}")
   public ResponseEntity<Note> getNotes(@PathVariable Long id) {
      Optional<Note> student = noteService.getById(id);
      return ResponseEntity.status(HttpStatus.OK).body(student.get());

   }

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

   @PutMapping("/{id}")
   public ResponseEntity<String> updateNote( @PathVariable Long id,@Valid @RequestBody NoteDto note) {

      if (noteService.getById(id).isPresent()) {

         Optional<Note> noteCreated =noteService.updateById(id, note);

         return ResponseEntity.status(HttpStatus.OK).body("Note modified, ".concat(noteCreated.toString()));
      } else {
         return ResponseEntity.status(HttpStatus.OK).body("Note not found");
      }

   }

   @DeleteMapping("/{id}")
   public ResponseEntity<String> removeNoteById(@PathVariable Long id) {

      if (noteService.getById(id).isPresent()) {
         noteService.removeById(id);
         return ResponseEntity.status(HttpStatus.OK).body("Note deleted");
      } else {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Note not found");
      }

   }
   @GetMapping("/students/{id}")
   public ResponseEntity<Optional<List<NoteDto>>> getNotesByStudentId(@PathVariable Long id) {
      return ResponseEntity.status(HttpStatus.OK).body(noteService.getNotesByStudentId(id));
   }
}

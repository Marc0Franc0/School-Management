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

import com.api.notemanagementapi.dto.TeacherDto;
import com.api.notemanagementapi.model.Teacher;
import com.api.notemanagementapi.service.TeacherService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

   @Autowired
   TeacherService teacherService;

   @GetMapping("/")
   public ResponseEntity<List<Teacher>> getAll() {
      return ResponseEntity.status(HttpStatus.OK).body(teacherService.getAll());
   }

   @GetMapping("/{id}")
   public ResponseEntity<Teacher> getTeacher(@PathVariable Long id) {
      Optional<Teacher> teacher = teacherService.getTeacherById(id);
      return ResponseEntity.status(HttpStatus.OK).body(teacher.get());

   }

   @PostMapping("/")
   public ResponseEntity<String> createTeacher(@Valid @RequestBody TeacherDto teacher) {
      Teacher teacherCreated = teacherService.createTeacher(teacher);
      if (teacherCreated != null) {
         return ResponseEntity.status(HttpStatus.OK).body("Teacher created");
      } else {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Teacher not created");
      }

   }

   @PutMapping("/{id}")
   public ResponseEntity<String> updateTeacher(@PathVariable Long id,@Valid  @RequestBody TeacherDto teacher) {
      if (teacherService.getTeacherById(id).isPresent()) {

         teacherService.updateTeacherById(id, teacher);

         return ResponseEntity.status(HttpStatus.OK).body("Teacher modified");
      } else {
         return ResponseEntity.status(HttpStatus.OK).body("Teacher not found");
      }

   }

   @DeleteMapping("/{id}")
   public ResponseEntity<String> removeTeacherById(@PathVariable Long id) {
      if (teacherService.getTeacherById(id).isPresent()) {
         teacherService.removeTeacherById(id);
         return ResponseEntity.status(HttpStatus.OK).body("Teacher deleted");
      } else {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Teacher not found");
      }
   }
}

package com.api.notemanagementapi.controller;

import java.util.List;
import java.util.Optional;

import com.api.notemanagementapi.service.TeacherService;
import com.api.notemanagementapi.service.crud.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import com.api.notemanagementapi.dto.TeacherDto;
import com.api.notemanagementapi.model.Teacher;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherController {

   @Autowired
   private TeacherService teacherService;

   @GetMapping("/")
   public ResponseEntity<List<Teacher>> getAll() {
      return ResponseEntity.status(HttpStatus.OK).body(teacherService.getAll());
   }

   @GetMapping("/{id}")
   public ResponseEntity<Teacher> getTeacher(@PathVariable Long id) {
      Optional<Teacher> teacher = teacherService.getById(id);
      return ResponseEntity.status(HttpStatus.OK).body(teacher.get());

   }

   @PostMapping("/")
   public ResponseEntity<String> createTeacher(@Valid @RequestBody TeacherDto teacher, BindingResult bindingResult) {
      if (bindingResult.hasErrors()) {
         return ResponseEntity
                 .status(HttpStatus.BAD_REQUEST)
                 .body(bindingResult.getFieldError().getDefaultMessage());
      } else {
         Teacher teacher1 =teacherService.create(teacher);
         return ResponseEntity
                 .status(HttpStatus.OK)
                 .body("Teacher created, ".concat(teacher1.toString()));
      }
   }

   @PutMapping("/{id}")
   public ResponseEntity<String> updateTeacher(@PathVariable Long id,@Valid  @RequestBody TeacherDto teacher
   ,BindingResult bindingResult) {
      if (teacherService.getById(id).isPresent()) {
         if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(bindingResult.getFieldError().getDefaultMessage());
         } else {
            Optional<Teacher> teacher1 =teacherService.updateById(id,teacher);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Teacher modified, ".concat(teacher1.toString()));
         }
      } else {
         return ResponseEntity.status(HttpStatus.OK).body("Teacher not found");
      }

   }

   @DeleteMapping("/{id}")
   public ResponseEntity<String> removeTeacherById(@PathVariable Long id) {
      if (teacherService.getById(id).isPresent()) {
         teacherService.removeById(id);
         return ResponseEntity.status(HttpStatus.OK).body("Teacher deleted");
      } else {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Teacher not found");
      }
   }
}

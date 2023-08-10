package com.api.notemanagementapi.security.controller;

import com.api.notemanagementapi.security.config.jwt.JwtAuthenticationFilter;
import com.api.notemanagementapi.security.config.jwt.JwtTokenProvider;
import com.api.notemanagementapi.security.dto.LoginDto;
import com.api.notemanagementapi.security.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.notemanagementapi.security.dto.RegisterDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

        @Autowired
        UserEntityService userEntityService;

        // Método para registrar un nuevo usuario
        @PostMapping("/register")
        public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterDto registerDto) {
                userEntityService.register(registerDto);
                return ResponseEntity.status(HttpStatus.OK).body("Usuario registrado");

        }
        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userEntityService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado");}

        @PostMapping("/login")
        public void loginUser(@Valid @RequestBody LoginDto loginDto) {
                userEntityService.authenticate(loginDto);

        }
}

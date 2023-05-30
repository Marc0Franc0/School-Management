package com.api.notemanagementapi.security.controller;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.notemanagementapi.security.dto.RegisterDto;
import com.api.notemanagementapi.security.model.ERole;
import com.api.notemanagementapi.security.model.RoleEntity;
import com.api.notemanagementapi.security.model.UserEntity;
import com.api.notemanagementapi.security.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        private UserRepository userRepository;

        // Método para registrar un nuevo usuario
        @PostMapping("/register")
        public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterDto registerDto) {
                /* Se almacena en roles todos los roles 
                que almacena registerDto al registrar un nuevo usuario*/
                Set<RoleEntity> roles = registerDto.getRoles().stream()
                                .map(role -> RoleEntity.builder()
                                                .name(ERole.valueOf(role))
                                                .build())
                                .collect(Collectors.toSet());
                //Se crea el nuevo usuario y se almacena en la base de datos
                UserEntity userEntity = UserEntity.builder()
                                .username(registerDto.getUsername())//Se establece username
                                //Se establece password y se encripta
                                .password(passwordEncoder.encode(registerDto.getPassword()))
                                .roles(roles)//Se establecen los roles
                                .build();

                userRepository.save(userEntity);

                return ResponseEntity.status(HttpStatus.OK).body("Usuario registrado");

        }
@DeleteMapping("/delete/{id}")
public ResponseEntity<String> deleteUser(@PathVariable Long id){
userRepository.deleteById(id);
return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado");

}

}

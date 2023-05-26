package com.api.notemanagementapi.security.controller;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.notemanagementapi.security.dto.LoginDto;
import com.api.notemanagementapi.security.dto.RegisterDto;
import com.api.notemanagementapi.security.jwt.JwtTokenProvider;
import com.api.notemanagementapi.security.model.Rol;
import com.api.notemanagementapi.security.model.User;
import com.api.notemanagementapi.security.repository.RolRepository;
import com.api.notemanagementapi.security.repository.UserRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // Método para registrar un usuario con role "USER"
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Existing user");
        } else {
            Rol rol = rolRepository.findByName("USER").get();
            User user = new User();
            user.setUsername(registerDto.getUsername());
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            user.setRoles(Collections.singletonList(rol));
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Successful registration");
        }

    }

    // Método para registrar un usuario con role "ADMIN"
    @PostMapping("/registeradm")
    public ResponseEntity<String> registerAdmin(@RequestBody RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Existing user");
        } else {
            Rol rol = rolRepository.findByName("ADMIN").get();
            User user = new User();
            user.setUsername(registerDto.getUsername());
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            user.setRoles(Collections.singletonList(rol));
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Successful registration");
        }

    }

    // Método para logear un user y obtener un token
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}

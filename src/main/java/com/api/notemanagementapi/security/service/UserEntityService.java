package com.api.notemanagementapi.security.service;

import com.api.notemanagementapi.security.config.jwt.JwtAuthenticationFilter;
import com.api.notemanagementapi.security.config.jwt.JwtTokenProvider;
import com.api.notemanagementapi.security.dto.LoginDto;
import com.api.notemanagementapi.security.dto.RegisterDto;
import com.api.notemanagementapi.security.model.ERole;
import com.api.notemanagementapi.security.model.RoleEntity;
import com.api.notemanagementapi.security.model.UserEntity;
import com.api.notemanagementapi.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserEntityService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    public UserEntity register(RegisterDto registerDto){
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

        return userRepository.save(userEntity);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void authenticate(LoginDto loginDto) {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtTokenProvider);
        jwtAuthenticationFilter.setUsernameParameter(loginDto.getUsername());
        jwtAuthenticationFilter.setPasswordParameter(loginDto.getPassword());

    }
}

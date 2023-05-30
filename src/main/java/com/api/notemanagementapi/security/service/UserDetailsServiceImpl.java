package com.api.notemanagementapi.security.service;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.api.notemanagementapi.security.model.UserEntity;
import com.api.notemanagementapi.security.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    // Método para traernos un usuario con todos sus datos por medio de sus username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Se obtiene el usuario de tipo UserEntity con su username
        // En el caso de no encontrarse se lanza una excepción de tipo
        // UsernameNotFoundException
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        //Obtención de los roles para luego ser almacenados en una lista de tipo GrantedAuthority
        Collection<? extends GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> 
                new SimpleGrantedAuthority("ROLE_".concat(role.getName().name())))
                .collect(Collectors.toSet());

        // Se devuelve un usuario de tipo User (No de tipo UserEntity)
        return new User(
                user.getUsername(),
                user.getPassword(),
                true,//Usuario activo
                true,//La cuenta no expira
                true,//Las credenciales no expiran
                true,//La cuenta no se bloquea
                authorities//Roles del usuario
                );
    }

}

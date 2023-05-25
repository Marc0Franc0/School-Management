package com.api.notemanagementapi.security.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.notemanagementapi.security.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    //Obtención de un usuario por medio de su nombre de usuario
    Optional<User>findByUsername(String username);
    //Verificación de la existencia de un usuario por medio de nombre de usuario
    Boolean existsByUsername(String username);
}

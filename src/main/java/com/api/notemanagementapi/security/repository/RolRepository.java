package com.api.notemanagementapi.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.notemanagementapi.security.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol,Long> {
   //Obtención de un rol por medio de su nombre
   Optional<Rol>findByName(String name); 
}

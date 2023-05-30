package com.api.notemanagementapi.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.notemanagementapi.security.model.RoleEntity;

@Repository
public interface RolRepository extends JpaRepository<RoleEntity,Long> {
}

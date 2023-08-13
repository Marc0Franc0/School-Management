package com.api.notemanagementapi.security.model;

import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique = true)
    private String username;
    @NotBlank
    private String password;
    /*Se utiliza fetchType en EAGER para que cada vez que se acceda o se 
    extraiga un usuario de la BD, este se traiga todos sus roles*/
    @ManyToMany(fetch = FetchType.EAGER,
    targetEntity = RoleEntity.class,
    cascade = CascadeType.PERSIST)
    @JoinTable(name = "users_roles",
    joinColumns = @JoinColumn(name="user_id")
    ,inverseJoinColumns = @JoinColumn(name="rol_id"))
    private Set<RoleEntity>roles;
}

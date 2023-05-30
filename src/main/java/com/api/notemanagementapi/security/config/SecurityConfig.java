package com.api.notemanagementapi.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.api.notemanagementapi.security.jwt.JwtAuthenticationFilter;
import com.api.notemanagementapi.security.jwt.JwtAuthorizationFilter;
import com.api.notemanagementapi.security.jwt.JwtTokenProvider;
import com.api.notemanagementapi.security.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    JwtAuthorizationFilter authorizationFilter;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    //Este objeto se encarga de la administración de la autenticación de los usuarios
    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder)
            throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    // Encriptación de las contraseñas
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * Se establece una cadena de filtros de seguridad en toda la aplicacion
     * Aquí se determinan los permisos según los roles de usuario para acceder a la
     * aplicación y demas configuraciones
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager)
            throws Exception {

        //Se utiliza la implementación para la autenticación 
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtTokenProvider);
        //Autenticación manager que se le establece(creado dentro de esta clase)
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        //Endpoint para autenticar
        jwtAuthenticationFilter.setFilterProcessesUrl("/api/auth/login");

        return http
                .csrf(config ->
                // Se deshabilita Cross-site request forgery
                 config.disable())
                .sessionManagement(session -> 
                // Permite la gestion de sesiones de tipo STATELESS
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                //Configuración de acceso a los endpoints 
                        auth -> {
                            auth.requestMatchers("/api/auth/**").permitAll();
                            auth.anyRequest().authenticated();
                        })
                //Se agrega el filtro de autenticación creado
                .addFilter(jwtAuthenticationFilter)
                //Se agrega el filtro necesario para autenticar utilizando un token
                //Se ejecuta luego del filtro de autenticación
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

}

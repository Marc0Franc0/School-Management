package com.api.notemanagementapi.security.config.jwt;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.api.notemanagementapi.security.service.UserDetailsServiceImpl;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*La función de esta clase será validar la información del token, en el caso de ser exitoso se
establecerá la autenticación de un usuario en la solicitud o en el contexto de seguridad    */
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(
            // Solicitud entrante
            @NonNull
            HttpServletRequest request,
            // Respuesta saliente
            @NonNull
            HttpServletResponse response,
            // Mecanismo para invocar el siguiente filtro en la siguiente cadena de filtros
            @NonNull
            FilterChain filterChain)
            throws ServletException, IOException {
        // Obtenemos el header "Authorization" el cual contiene el token
        String tokenHeader = request.getHeader("Authorization");

        // Validación de la información del token
        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            //Se obtiene el token del header obtenido con un substring
            String token = tokenHeader.substring(7, tokenHeader.length());
            if (jwtTokenProvider.isTokenValid(token)) {
            /* Obtención de el nombre de usuario contenido en "token"
             y se asigna  a "username"*/
                String username = jwtTokenProvider.getUsernameFromToken(token);
                 /*
                 * Se crea el objeto userDetails el cual contendrá todos
                 * los detalles de nuestro username según el método loadUserByUsername
                 */
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
           
                //Se crea la autenticación
                UsernamePasswordAuthenticationToken authenticationToken = 
                new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
                //Se obtiene en el contexto de seguridad de la aplicación la autenticación creada
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

           

        }
        // Permite que la solicitud continue hacia el siguiente filtro en la cadena de filtro
        filterChain.doFilter(request, response);
    }
}

package com.api.notemanagementapi.security.jwt;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import com.api.notemanagementapi.security.service.CustomUserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*La función de esta clase será validar la información del token, en el caso de ser exitoso se
establecerá la autenticación de un usuario en la solicitud o en el contexto de seguridad    */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsServiceImpl;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    /*
     * Método para extraer el token JWT de la cabecera de nuestra petición
     * Http("Authorization")
     * luego lo validaremos y finalmente se retornará el mismo en el caso de
     * existir, caso constrario retornara null
     */
    private String getRequestToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            /*
             * Si se encuentra el token JWT, se devuelve una subcadena de "bearerToken"
             * que comienza después de los primeros 7 caracteres hasta el final de la cadena
             */
            return bearerToken.substring(7, bearerToken.length());
        } else {
            return null;
        }
    }

    @Override
    protected void doFilterInternal(
            // Solicitud entrante
            HttpServletRequest request,
            // Respuesta saliente
            HttpServletResponse response,
            // Mecanismo para invocar el siguiente filtro en la siguiente cadena de filtros
            FilterChain filterChain)
            throws ServletException, IOException {
        // Obtenemos los datos del token utilizando el método ya desarrollado arriba
        String token = getRequestToken(request);
        // Validación de la información del token
        if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
            // Asignación de el nombre de usuario contenido en el objeto "token" y se asigna
            // a la variable "username"
            String username = jwtTokenProvider.getUsername(token);
            /*
             * Se crea el objeto userDetails el cual contendrá todos
             * los detalles de nuestro username según el método loadUserByUsername
             */
            UserDetails userDetails = customUserDetailsServiceImpl.loadUserByUsername(username);
            // Se carga una lista de String con los roles alojados en la base de datos
            List<String> userRoles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
            // Se comprueba que el usuario autenticado posee alguno de los siguientes roles
            // alojados en la base de datos
            if (userRoles.contains("USER") || userRoles.contains("ADMIN")) {
                /*
                 * Se creaa el objeto UsernamePasswordAuthenticationToken el cual contendrá los
                 * detalles de autenticación del usuario
                 */
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null, userDetails.getAuthorities());
                        /*
                         * Se establece la información adicional de la autenticación, como 
                         * por ejemplo la dirección ip del usuario, o el agente de usuario para hacer la solicitud etc.
                         */
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //Se establece el objeto anterior (autenticación del usuario) en el contexto de seguridad
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }
            //Permite que la solicitud continue hacia el siguiente filtro en la cadena de filtro.
            filterChain.doFilter(request, response);
        }
    }

}

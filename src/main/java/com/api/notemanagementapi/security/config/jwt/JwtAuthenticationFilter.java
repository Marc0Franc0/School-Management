package com.api.notemanagementapi.security.config.jwt;

import com.api.notemanagementapi.security.model.UserEntity;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private JwtTokenProvider jwtTokenProvider;
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // Método para intentar autenticar
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {

        UserEntity userEntity = null;
        String username = "";
        String password = "";
        try {
            // Se convierte en objeto UserEntity el json con los datos utilizando
            // ObjectMapper
            userEntity = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);
            // Se obtienen el username y password del usuario y se almacenan
            username = userEntity.getUsername();
            password = userEntity.getPassword();
        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Se crea la autenticación
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                password);

        // Se establece la autenticación con la autenticación creada
        return getAuthenticationManager().authenticate(authenticationToken);
    }

    // Se accede a este método en el caso de haber una autenticación correcta
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        /* Se obtiene el objeto con los datos del usuario autenticado y se almacena
        en un objeto de tipo User (NO ES DE TIPO UserEntity)*/
        User user = (User) authResult.getPrincipal();
        // Se genera el token a partir del username del usuario ya autenticado
        String token = jwtTokenProvider.generateAccessToken(user.getUsername());
        // Se eestablece un header de autorización que obtiene el token
        response.addHeader("Authorization", token);
        
        //Body de la respuesta
        Map<String, Object> httpResponse = new HashMap<>();
        //Se envia el token
        httpResponse.put("token", token);
        //Mensaje de autenticación correcta
        httpResponse.put("Message", "Autenticacion Correcta");
        //Username del token
        httpResponse.put("Username", user.getUsername());

        //Se establece el response
        response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
        //Estado de la solicitud
        response.setStatus(HttpStatus.OK.value());
        //Contenido de la respuesta
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().flush();

        super.successfulAuthentication(request, response, chain, authResult);
    }
}

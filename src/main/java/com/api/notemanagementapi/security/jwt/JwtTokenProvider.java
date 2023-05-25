package com.api.notemanagementapi.security.jwt;

import java.util.Date;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
    //Método para la creación de un token
    public String generateToken(Authentication authentication){
       
        String username = authentication.getName();
        Date time = new Date();
        Date tokenExpiration = new Date(time.getTime() + SecurityConstants.JWT_EXPIRATION_TOKEN);

        //Generación de token
         String token = Jwts.builder()
        .setSubject(username)//Nombre de usuario que esta iniciando sesión
        .setIssuedAt(new Date())//Fecha de emisión del token en el momento actual
        .setExpiration(tokenExpiration)//Fecha de caducidad del token
        .signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_FIRMA)/*Se utiliza este método para firmar
        nuestro token y de esta manera evitar la manipulación o modificación de este*/
        .compact();//Se contruye el token y se convierte en una cadena compacta
        return token;
    }

    
//Método para extraer el nombre de usuario de un token 
public String getUsername (String token){
    Claims claims = Jwts.parser()//El método parser se utiliza con el fin de analizar el token
    .setSigningKey(SecurityConstants.JWT_FIRMA)//Establece la clave de firma, que se utiliza para verificar la firma del token
    .parseClaimsJws(token)//Se utiliza para verificar la firma del token, a partir del String "token"
    .getBody();/*Se obtiene el claims(cuerpo) ya verificado del token el cual contendrá la información de
    nombre de usuario, fecha de expiración y firma del token*/
    return claims.getSubject();//Se retorna el nombre de usuario
}

//Método para validar un token
public Boolean validateToken(String token){
    try {
        //Validación del token por medio de la firma que contiene el String token(token)
        //Si son idénticas validara el token o caso contrario saltara la excepción de abajo
        Jwts.parser().setSigningKey(SecurityConstants.JWT_FIRMA).parseClaimsJws(token);
        return true;
    } catch (Exception e) {
        throw new AuthenticationCredentialsNotFoundException("Jwt expired");
    }
}
}

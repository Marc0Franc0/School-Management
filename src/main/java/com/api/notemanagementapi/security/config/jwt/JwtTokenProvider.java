package com.api.notemanagementapi.security.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
@Slf4j
public class JwtTokenProvider {

    // Se utiliza para firmar los tokens
    @Value("${jwt.secret.key}")
    private String secretKey;

    // Se utiliza para el tiempo de validez de los tokens
    @Value("${jwt.time.expiration}")
    private String timeExpiration;

    // Método para la generación de un token de acceso
    public String generateAccessToken(String username) {
        return Jwts.builder()
                // Username del token
                .setSubject(username)
                // Fecha de creación del token
                .setIssuedAt(new Date(System.currentTimeMillis()))
                // Expiración del token
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
                // Firma del token
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Validar el token de acceso
    public boolean isTokenValid(String token) {
        try {
            // El método parser se utiliza para leer un token
            Jwts.parserBuilder()
                    .setSigningKey(getSignatureKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (Exception e) {
            log.error("Token invalido, error: ".concat(e.getMessage()));
            return false;
        }
    }

    // Método para obtener el username del token
    public String getUsernameFromToken(String token) {
        // Se devuelve el valor de username
        return getClaim(token, Claims::getSubject);
    }

    // Método para obtener un solo claim
    public <T> T getClaim(String token, Function<Claims, T> claimsTFunction) {
        // Se obtienen todos los claims
        Claims claims = extractAllClaims(token);
        // Se devuelve el valor
        return claimsTFunction.apply(claims);
    }

    // Método para obtener todos los claims del token
    public Claims extractAllClaims(String token) {
        // El método parser se utiliza para leer un token
        return Jwts.parserBuilder()
                .setSigningKey(getSignatureKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Método para obtener la firma del token
    public Key getSignatureKey() {
        // Se docodifica la secretKey
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        // Se vuelve a codificar en una codificación util a la hora de generar un token
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

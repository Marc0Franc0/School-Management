package com.api.notemanagementapi.security.dto;

import lombok.Data;

//Clase encargada de retornar información con el token y el tipo de información del mismo
@Data
public class AuthResponseDto {
    public AuthResponseDto(String accessToken, String tokenType) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }
    private String accessToken;
    private String tokenType = "Bearer ";
    
}

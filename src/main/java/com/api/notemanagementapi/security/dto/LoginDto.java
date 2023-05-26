package com.api.notemanagementapi.security.dto;

import lombok.Data;

@Data
public class LoginDto {
    
    private String username;
    private String password;
}

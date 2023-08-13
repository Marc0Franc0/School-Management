package com.api.notemanagementapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class SQLException {
    //Validaciones al registrarse (username duplicado en la base de datos)
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity captureSQLIntegrityConstraintViolationException
    (SQLIntegrityConstraintViolationException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message: "
                .concat("Uno o más de los datos enviados ya existen, pruebe con otros."));
    }

}
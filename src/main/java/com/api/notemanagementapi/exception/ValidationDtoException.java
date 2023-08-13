package com.api.notemanagementapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ValidationDtoException{
    //Validaciones al registrarse-> entrada de datos vacios o nulos
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity captureMethodArgumentNotValidException
    (MethodArgumentNotValidException e){
        List<String> errors = e
                .getBindingResult()
                .getFieldErrors()
                .stream().map( FieldError ::getDefaultMessage)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message: ".concat(errors.toString()));
    }

    //Formato JSON recibido
    @ExceptionHandler( HttpMessageNotReadableException.class)
    public ResponseEntity captureHttpMessageNotReadableException
    (HttpMessageNotReadableException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Message: ".concat(e.getMessage()));
    }
    //Parametro de json no permitido
    @ExceptionHandler( IllegalArgumentException.class)
    public ResponseEntity captureHttpMessageNotReadableException
            (IllegalArgumentException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Message: ".concat("Uno de los parámetros enviados no es permitido"));
    }
}

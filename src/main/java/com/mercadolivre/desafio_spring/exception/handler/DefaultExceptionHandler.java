package com.mercadolivre.desafio_spring.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> defaultHandler(Exception e){
        return ResponseEntity.internalServerError().build();
    }

}

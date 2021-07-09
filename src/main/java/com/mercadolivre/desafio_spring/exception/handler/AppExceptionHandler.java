package com.mercadolivre.desafio_spring.exception.handler;

import com.mercadolivre.desafio_spring.exception.ConflictException;
import com.mercadolivre.desafio_spring.exception.PersistenceException;
import com.mercadolivre.desafio_spring.exception.ValidateException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler({PersistenceException.class, ValidateException.class, ConflictException.class, NoSuchElementException.class})
    public ResponseEntity<?> defaultHandler(RuntimeException e){
        return ResponseEntity.badRequest().build();
    }

}

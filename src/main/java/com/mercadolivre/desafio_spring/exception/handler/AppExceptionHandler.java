package com.mercadolivre.desafio_spring.exception.handler;

import com.mercadolivre.desafio_spring.dto.ErrorMessageDTO;
import com.mercadolivre.desafio_spring.exception.ConflictException;
import com.mercadolivre.desafio_spring.exception.ValidateException;
import com.mercadolivre.desafio_spring.exception.PersistenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<ErrorMessageDTO> persistenceHandler(PersistenceException e){
        return ResponseEntity.internalServerError().body(new ErrorMessageDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
    }

    @ExceptionHandler({ValidateException.class})
    public ResponseEntity<ErrorMessageDTO> validateHandler(ValidateException e){
        return ResponseEntity.badRequest().body(new ErrorMessageDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorMessageDTO> conflictHandler(ConflictException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessageDTO(HttpStatus.CONFLICT.value(), e.getMessage()));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorMessageDTO> noSuchElementHandler(NoSuchElementException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessageDTO(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }

}

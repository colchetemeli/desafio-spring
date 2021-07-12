package com.mercadolivre.desafio_spring.exception.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ValidateExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class,  MethodArgumentTypeMismatchException.class})
    public ResponseEntity<?> defaultHandler(Exception e){
        return ResponseEntity.badRequest().build();
    }

}

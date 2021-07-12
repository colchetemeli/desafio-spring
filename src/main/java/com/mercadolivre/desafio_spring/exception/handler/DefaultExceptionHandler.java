package com.mercadolivre.desafio_spring.exception.handler;

import com.mercadolivre.desafio_spring.dto.ErrorMessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> defaultHandler(){
        return ResponseEntity.internalServerError().body(new ErrorMessageDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error"));
    }

}

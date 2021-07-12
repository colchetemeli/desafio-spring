package com.mercadolivre.desafio_spring.exception.handler;

import com.mercadolivre.desafio_spring.dto.ErrorMessageDTO;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ValidateExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorMessageDTO> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e){
        return ResponseEntity.badRequest().body(new ErrorMessageDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageDTO> methodArgumentNotValidHandler(MethodArgumentNotValidException e){

        List<String> fieldErrorsString = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(f -> f.getField()+": "+f.getDefaultMessage())
                .collect(Collectors.toList());

        String stringErrors = String.join(", ", fieldErrorsString);

        return ResponseEntity.badRequest().body(new ErrorMessageDTO(HttpStatus.BAD_REQUEST.value(), stringErrors));
    }

}

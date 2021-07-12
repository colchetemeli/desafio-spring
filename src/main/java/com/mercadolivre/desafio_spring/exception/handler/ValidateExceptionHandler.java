package com.mercadolivre.desafio_spring.exception.handler;

import com.mercadolivre.desafio_spring.dto.ErrorMessageDTO;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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

import java.util.ArrayList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ValidateExceptionHandler {

    private MessageSource messageSource;

    @ExceptionHandler({MethodArgumentNotValidException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<?> defaultHandler(MethodArgumentNotValidException e){
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        List<ErrorMessageDTO> processFieldErrors = processFieldErrors(fieldErrors);
        System.out.println("passou");
        return ResponseEntity.badRequest().body(processFieldErrors);
    }

    private List<ErrorMessageDTO> processFieldErrors(List<FieldError> fieldErrors) {
        List<ErrorMessageDTO> listaDtos = new ArrayList<>();
        for (FieldError fieldError: fieldErrors) {
            String mensagemDeErro = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            listaDtos.add(new ErrorMessageDTO(HttpStatus.BAD_REQUEST.value(), mensagemDeErro));
        }
        return listaDtos;
    }

}

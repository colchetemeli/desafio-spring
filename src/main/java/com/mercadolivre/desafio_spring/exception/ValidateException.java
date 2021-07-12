package com.mercadolivre.desafio_spring.exception;

public class ValidateException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ValidateException() {
        super();
    }

    public ValidateException(String message) {
        super(message);
    }
}

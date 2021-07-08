package com.mercadolivre.desafio_spring.exceptions;

public class CreatePostException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public CreatePostException() {
    }

    public CreatePostException(String message) {
        super(message);
    }
}

package com.mercadolivre.desafio_spring.exception;

public class FollowUnfollowException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FollowUnfollowException() {
        super();
    }

    public FollowUnfollowException(String message) {
        super(message);
    }
}

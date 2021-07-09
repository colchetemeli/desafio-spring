package com.mercadolivre.desafio_spring.util;

import com.mercadolivre.desafio_spring.entity.User;
import com.mercadolivre.desafio_spring.exception.NotFoundException;

import java.util.Objects;

public class UserValidation {

    public static void validateIfUserExists(User user) {
        if (Objects.isNull(user)) {
            throw new NotFoundException("O usuário não existe.");
        }
    }
}

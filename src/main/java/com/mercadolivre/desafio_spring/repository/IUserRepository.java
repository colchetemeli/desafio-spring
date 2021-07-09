package com.mercadolivre.desafio_spring.repository;

import com.mercadolivre.desafio_spring.entity.User;

import java.util.Optional;

public interface IUserRepository {

    Optional<User> fetchById(int userId);
    User update(int userId, User user);
    
}

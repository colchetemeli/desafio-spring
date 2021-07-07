package com.mercadolivre.desafio_spring.repository;

import com.mercadolivre.desafio_spring.entity.User;

public interface IUserRepository {

    User fetchById(int userId);
    User update(int userId, User user);
    
}

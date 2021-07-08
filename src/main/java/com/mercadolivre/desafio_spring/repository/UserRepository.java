package com.mercadolivre.desafio_spring.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolivre.desafio_spring.entity.User;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements IUserRepository {

    private ObjectMapper mapper = new ObjectMapper();

    private static final File FILE = new File("src/main/resources/repository/users.json");

    @Override
    public User fetchById(int userId) {

        return this.getUsers().stream().filter(user -> user.getId() == userId).findFirst().orElse(null);
    }

    @Override
    public User update(int userId, User user) {
        List<User> users = getUsers();

        if (users.removeIf(user1 ->  user1.getId() == userId)) {
            users.add(user);
            try {
                mapper.writeValue(FILE, users);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return user;
    }


    private List<User> getUsers(){
        List<User> users = new ArrayList<>();
        try {
            users = mapper.readValue(FILE, new TypeReference<>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
}
